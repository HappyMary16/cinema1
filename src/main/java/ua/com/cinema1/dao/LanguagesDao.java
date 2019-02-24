package ua.com.cinema1.dao;

import ua.com.cinema1.model.Language;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LanguagesDao extends Dao<Language> {

    private final String INSERT = "INSERT INTO language (language) VALUES (?)";
    private final String UPDATE = "UPDATE language SET language = ? WHERE id = ?";

    private static LanguagesDao seanceDao;

    private LanguagesDao(Class<Language> type) {
        super(type);
    }

    public static LanguagesDao getInstance() {
        if (seanceDao == null) {
            seanceDao = new LanguagesDao(Language.class);
        }
        return seanceDao;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Language entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getLanguage());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Language entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getLanguage());
        preparedStatement.setInt(2, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<Language> readAll(ResultSet resultSet) throws SQLException {
        List<Language> result = new LinkedList<>();

        while (resultSet.next()) {
            Language entity = new Language();
            entity.setId(resultSet.getInt("id"));
            entity.setLanguage(resultSet.getString("language"));
            result.add(entity);
        }
        return result;
    }

}
