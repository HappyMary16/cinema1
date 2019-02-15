package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ActorDao extends Dao<Actor>{

    private static final String INSERT = "INSERT INTO person (first_name, last_name, role_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE person SET first_name = ?, last_name = ?, role_id = ? WHERE id = ?";
    public static final String SELECT_ALL = "SELECT * FROM person WHERE role_id = 3";

    private static ActorDao actorDao;

    private ActorDao(Class<Actor> type) {
        super(type);
    }

    public static ActorDao getInstance() {
        if (actorDao == null) {
            actorDao = new ActorDao(Actor.class);
        }
        return actorDao;
    }

    @Override
    public List<Actor> getAll() {

        List<Actor> result = null;
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
    protected PreparedStatement createInsertStatement(Connection connection, Actor entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setInt(3, Role.ACTOR.getId());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Actor entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setInt(3, Role.ACTOR.getId());
        preparedStatement.setInt(4, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<Actor> readAll(ResultSet resultSet) throws SQLException {
        List<Actor> result = new LinkedList<>();

        while (resultSet.next()) {
            Actor actor = new Actor();
            actor.setId(resultSet.getInt("id"));
            actor.setFirstName(resultSet.getString("first_name"));
            actor.setLastName(resultSet.getString("last_name"));
            result.add(actor);
        }
        return result;
    }
}
