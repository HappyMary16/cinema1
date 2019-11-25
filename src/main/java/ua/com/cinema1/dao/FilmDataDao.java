package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FilmDataDao<T extends Entity> implements IFilmDataDao<T> {
    private String type;
    private DataSource dataSource;

    public static final String GET_ALL_BY_ID = "SELECT * FROM film_%s WHERE film_id = ?";
    public static final String DELETE_ALL_BY_ID = "DELETE FROM film_%s WHERE film_id = ?";
    public static final String INSERT_ALL_BY_ID = "INSERT INTO film_%s (film_id, %s_id) VALUES (?, ?)";

    public FilmDataDao(Class<T> type) {
        this.type = type.getSimpleName().toLowerCase();
        dataSource = DataSource.getInstance();
    }

    @Override
    public void insertDataByFilmId(List<T> data, int filmId, Dao<T> dao) {
        String sql = String.format(INSERT_ALL_BY_ID, type, type);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (T g :
                    data) {
                if (g.getId() == -1) {
                    dao.insert(g);
                }
                preparedStatement.setInt(1, filmId);
                preparedStatement.setInt(2, g.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAllByFilmId(int filmId, Dao<T> dao) {
            List<T> result = new LinkedList<>();
            String sql = String.format(GET_ALL_BY_ID, type);

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, filmId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    result.add(dao
                            .getById(resultSet
                                    .getInt(type + "_id")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
    }

    @Override
    public void deleteAllByFilmId(int filmId) {
        String sql = String.format(DELETE_ALL_BY_ID, type);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, filmId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAllByFilmId(int filmId, List<T> newData, Dao<T> dao) {
        deleteAllByFilmId(filmId);
        insertDataByFilmId(newData, filmId, dao);
    }
}
