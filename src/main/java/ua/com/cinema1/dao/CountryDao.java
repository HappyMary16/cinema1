package ua.com.cinema1.dao;

import ua.com.cinema1.model.Country;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CountryDao extends Dao<Country>{

    private static final String INSERT = "INSERT INTO country (country) VALUES (?)";
    private static final String UPDATE = "UPDATE country SET country = ? WHERE id = ?";

    private static CountryDao countryDao;

    private CountryDao(Class<Country> type) {
        super(type);
    }

    public static CountryDao getInstance() {
        if (countryDao == null) {
            countryDao = new CountryDao(Country.class);
        }
        return countryDao;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Country entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getCountry());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Country entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getCountry());
        preparedStatement.setInt(2, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<Country> readAll(ResultSet resultSet) throws SQLException {
        List<Country> result = new LinkedList<>();

        while (resultSet.next()) {
            Country country = new Country();
            country.setId(resultSet.getInt("id"));
            country.setCountry(resultSet.getString("country"));
            result.add(country);
        }
        return result;
    }
}
