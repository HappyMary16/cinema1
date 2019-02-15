package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.Studio;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudioDao extends Dao<Studio> {
    private static final String INSERT = "INSERT INTO studio (studio) VALUES (?)";
    private static final String UPDATE = "UPDATE studio SET studio = ? WHERE id = ?";
    public static final String SELECT_ALL = "SELECT * FROM studio";

    private static StudioDao studioDao;

    private StudioDao(Class<Studio> type) {
        super(type);
    }

    public static StudioDao getInstance() {
        if (studioDao == null) {
            studioDao = new StudioDao(Studio.class);
        }
        return studioDao;
    }

    @Override
    public List<Studio> getAll() {

        List<Studio> result = null;
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            result = readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Studio entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getStudio());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Studio entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getStudio());
        preparedStatement.setInt(2, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<Studio> readAll(ResultSet resultSet) throws SQLException {
        List<Studio> result = new LinkedList<>();

        while (resultSet.next()) {
            Studio studio = new Studio();
            studio.setId(resultSet.getInt("id"));
            studio.setStudio(resultSet.getString("studio"));
            result.add(studio);
        }
        return result;
    }
}
