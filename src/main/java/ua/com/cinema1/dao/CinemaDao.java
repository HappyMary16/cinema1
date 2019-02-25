package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.Cinema;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CinemaDao extends Dao<Cinema>{
    private static final String INSERT = "INSERT INTO cinema (name, describe, address, coordinate_x, coordinate_y) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cinema SET name = ?, describe = ?, address = ?, coordinate_x = ?, coordinate_y = ?";
    private static final String INSERT_DATA = "INSERT INTO %s (%s) VALUES (?)";
    private static final String UPDATE_DATA = "UPDATE %s SET %s = ? WHERE id = ?";
    private static CinemaDao cinemaDao;

    private CinemaDao(Class<Cinema> type) {
        super(type);
    }

    public static CinemaDao getInstance() {
        if (cinemaDao == null) {
            cinemaDao = new CinemaDao(Cinema.class);
        }
        return cinemaDao;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Cinema entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getDescribe());
        preparedStatement.setString(3, entity.getAddress());
        preparedStatement.setDouble(4, entity.getCoordinateX());
        preparedStatement.setDouble(5, entity.getCoordinateY());

        insertData(entity.getPhoneNumbers(),
                connection,
                String.format(INSERT_DATA, "phone_numbers", "phone_number"));
        insertData(entity.getEmails(),
                connection,
                String.format(INSERT_DATA, "emails", "email"));
        List<String> photos = new LinkedList<>();
        entity.getPhotos().forEach(e -> photos.add(e.toString()));
        insertData(photos,
                connection,
                String.format(INSERT_DATA, "photos", "photo"));

        return preparedStatement;
    }

    private void insertData(List<String> data, Connection connection, String sql) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (String s :
                    data) {
                preparedStatement.setString(1, s);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Cinema entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getDescribe());
        preparedStatement.setString(3, entity.getAddress());
        preparedStatement.setDouble(4, entity.getCoordinateX());
        preparedStatement.setDouble(5, entity.getCoordinateY());

        updateData(entity.getPhoneNumbers(),
                connection,
                String.format(UPDATE_DATA, "phone_numbers", "phone_number"));
        updateData(entity.getEmails(),
                connection,
                String.format(INSERT_DATA, "emails", "email"));
        List<String> photos = new ArrayList<>();
        entity.getPhotos().forEach(e -> photos.add(e.toString()));
        updateData(photos,
                connection,
                String.format(INSERT_DATA, "photos", "photo"));

        return preparedStatement;
    }

    private void updateData(List<String> data, Connection connection, String sql) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < data.size(); i++) {
                preparedStatement.setString(1, data.get(i));
                preparedStatement.setInt(2, i);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Cinema> readAll(ResultSet resultSet) throws SQLException {
        List<Cinema> result = new LinkedList<>();

        while (resultSet.next()) {
            Cinema cinema = new Cinema();

            cinema.setId(resultSet.getInt("id"));
            cinema.setName(resultSet.getString("name"));
            cinema.setDescribe(resultSet.getString("describe"));
            cinema.setAddress(resultSet.getString("address"));
            cinema.setCoordinateX(resultSet.getDouble("coordinate_x"));
            cinema.setCoordinateY(resultSet.getDouble("coordinate_y"));
            cinema.setEmails(getData("emails"));
            cinema.setPhoneNumbers(getData("phone_numbers"));
            List<File> photos = new LinkedList<>();
            getData("photos").forEach(e -> photos.add(new File(e)));
            cinema.setPhotos(photos);

            result.add(cinema);
        }
        return result;
    }

    private List<String> getData(String tableName) {
        String sql = String.format(SELECT_ALL, tableName);

        List<String> result = new LinkedList<>();
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.add(resultSet.getString(tableName.substring(0, tableName.length() - 1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
