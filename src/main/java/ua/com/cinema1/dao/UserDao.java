package ua.com.cinema1.dao;

import ua.com.cinema1.model.Role;
import ua.com.cinema1.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDao extends Dao<User>{

    private final String INSERT = "INSERT INTO user (first_name, last_name, login, password, phone, email, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE user SET first_name = ?, last_name = ?, login = ?, password = ?, phone = ?, email = ?, role_id = ? WHERE id = ?";

    private static UserDao userDao;

    private UserDao(Class<User> type) {
        super(type);
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao(User.class);
        }
        return userDao;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getLogin());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.setString(5, entity.getPhone());
        preparedStatement.setString(6, entity.getEmail());
        preparedStatement.setInt(7, entity.getRole().getId());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getLogin());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.setString(5, entity.getPhone());
        preparedStatement.setString(6, entity.getEmail());
        preparedStatement.setInt(7, entity.getRole().getId());
        preparedStatement.setInt(8, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<User> readAll(ResultSet resultSet) throws SQLException {
        List<User> result = new LinkedList<>();

        while (resultSet.next()) {
            User entity = new User();
            entity.setId(resultSet.getInt("id"));
            entity.setFirstName(resultSet.getString("first_name"));
            entity.setLastName(resultSet.getString("last_name"));
            entity.setLogin(resultSet.getString("login"));
            entity.setPassword(resultSet.getString("password"));
            entity.setPhone(resultSet.getString("phone"));
            entity.setEmail(resultSet.getString("email"));
            entity.setRole(Role.getInstance(resultSet.getInt("role_id")));
            result.add(entity);
        }
        return result;
    }
}
