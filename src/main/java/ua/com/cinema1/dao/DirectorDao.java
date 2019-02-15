package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.Director;
import ua.com.cinema1.model.Role;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DirectorDao extends Dao<Director>{

    private final String INSERT = "INSERT INTO person (first_name, last_name, role_id) VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE person SET first_name = ?, last_name = ? WHERE id = ?";
    public static final String SELECT_ALL = "SELECT * FROM person WHERE role_id = 4";

    private static DirectorDao directorDao;

    private DirectorDao(Class<Director> type) {
        super(type);
    }

    public static DirectorDao getInstance() {
        if (directorDao == null) {
            directorDao = new DirectorDao(Director.class);
        }
        return directorDao;
    }

    @Override
    public List<Director> getAll() {

        List<Director> result = null;
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
    protected PreparedStatement createInsertStatement(Connection connection, Director entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setInt(3, Role.DIRECTOR.getId());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Director entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setInt(3, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<Director> readAll(ResultSet resultSet) throws SQLException {
        List<Director> result = new LinkedList<>();

        while (resultSet.next()) {
            Director entity = new Director();
            entity.setId(resultSet.getInt("id"));
            entity.setFirstName(resultSet.getString("first_name"));
            entity.setLastName(resultSet.getString("last_name"));
            result.add(entity);
        }
        return result;
    }
}
