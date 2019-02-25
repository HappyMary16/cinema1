package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.Hall;
import ua.com.cinema1.model.Place;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HallDao extends Dao<Hall> {

    private final String INSERT = "INSERT INTO hall (hall_name, width, height) VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE hall SET hall_name = ?, width = ?, height = ?, placement_id = ? WHERE id = ?";

    private static HallDao hallDao;

    private HallDao(Class<Hall> type) {
        super(type);
    }

    public static HallDao getInstance() {
        if (hallDao == null) {
            hallDao = new HallDao(Hall.class);
        }
        return hallDao;
    }

    @Override
    public int insert(Hall entity) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = createInsertStatement(connection, entity)) {
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }

            PlacementDao.getInstance().insertAll(entity.getPlacement(), entity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getId();
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Hall entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getWidth());
        preparedStatement.setInt(3, entity.getHeight());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Hall entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getWidth());
        preparedStatement.setInt(3, entity.getHeight());
        preparedStatement.setInt(4, 1); //placement ????
        preparedStatement.setInt(5, entity.getId());
        PlacementDao.getInstance().updateAll(entity.getPlacement(), entity.getId());
        return preparedStatement;
    }

    @Override
    protected List<Hall> readAll(ResultSet resultSet) throws SQLException {
        List<Hall> result = new LinkedList<>();

        while (resultSet.next()) {
            Hall entity = new Hall();
            entity.setId(resultSet.getInt("id"));
            entity.setName(resultSet.getString("hall_name"));
            entity.setHeight(resultSet.getInt("height"));
            entity.setWidth(resultSet.getInt("width"));
            entity.setPlacement(new boolean[entity.getWidth()][entity.getHeight()]);
            PlacementDao.getInstance().getAll(entity.getPlacement(), entity.getId());

            List<Place> places = new LinkedList<>();
            for (int i = 0, numPlace = 1; i < entity.getPlacement().length; i++) {
                for (int j = 0; j < entity.getPlacement()[i].length; j++) {
                    if (entity.getPlacement()[i][j]) {
                        places.add(new Place(i, numPlace++));
                    }
                }
            }
            entity.setPlaces(places);
            result.add(entity);
        }

        return result;
    }

    private static class PlacementDao implements IPlacementDao {

        private static final String SELECT_ALL_BY_HALL_ID = "SELECT * FROM placement WHERE id = ?";
        private static final String INSERT_ALL_BY_HALL_ID = "INSERT INTO placement (id, row_num, column_num) VALUES (?, ?, ?)";
        public static final String DELETE_ALL_BY_HALL_ID = "DELETE FROM placement WHERE id = ?";

        private static PlacementDao placementDao = new PlacementDao();

        private PlacementDao() {
        }

        public static PlacementDao getInstance() {
            return placementDao;
        }

        @Override
        public void getAll(boolean[][] places, int hallId) {

            try (Connection connection = DataSource.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_HALL_ID)) {
                preparedStatement.setInt(1, hallId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    places[resultSet.getInt("column_num")][resultSet.getInt("row_num")] = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void insertAll(boolean[][] places, int hallId) {
            try (Connection connection = DataSource.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ALL_BY_HALL_ID)) {

                preparedStatement.setInt(1, hallId);
                connection.setAutoCommit(false);

                for (int i = 0; i < places.length; i++) {
                    for (int j = 0; j < places[i].length; j++) {
                        if (places[i][j]) {
                            preparedStatement.setInt(2, i);
                            preparedStatement.setInt(3, j);
                            preparedStatement.executeUpdate();
                        }
                    }
                }
                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void deleteAll(int id) {
            try (Connection connection = DataSource.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_BY_HALL_ID);) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void updateAll(boolean[][] places, int id) {
            deleteAll(id);
            insertAll(places, id);
        }
    }
}
