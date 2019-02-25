package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.*;

import java.io.File;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FilmDao extends Dao<Film>{

    private final String INSERT = "INSERT INTO film (title, description, min_age, duration, film_language, first_seance, last_seance, small_poster, big_poster, trailer_link, year) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE film SET title = ?, description = ?, min_age = ?, duration = ?, film_language = ?, first_seance = ?, last_seance = ?, small_poster = ?, big_poster = ?, trailer_link = ?, year = ? WHERE id = ?";

    private static FilmDao filmDao;
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    private FilmDao(Class type) {
        super(type);
    }

    public static FilmDao getInstance() {
        if (filmDao == null) {
            filmDao = new FilmDao(Film.class);
        }
        return filmDao;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Film entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, entity.getTitle());
        preparedStatement.setString(2, entity.getDescribe());
        preparedStatement.setInt(3, entity.getMinAge());
        preparedStatement.setLong(4, entity.getDuration());
        preparedStatement.setInt(5, entity.getLanguage().getId());
        preparedStatement.setDate(6, new Date(entity.getFirstSeance().getTime()));
        preparedStatement.setDate(7, new Date(entity.getLastSeance().getTime()));
        preparedStatement.setString(8, entity.getSmallPoster().toString());
        preparedStatement.setString(9, entity.getBigPoster().toString());
        preparedStatement.setString(10, entity.getTrailerLink());
        preparedStatement.setInt(11, entity.getYear());

        return preparedStatement;
    }

    @Override
    public int insert(Film entity) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = createInsertStatement(connection, entity)) {

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getId();
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Film entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setString(1, entity.getTitle());
        preparedStatement.setString(2, entity.getDescribe());
        preparedStatement.setInt(3, entity.getMinAge());
        preparedStatement.setLong(4, entity.getDuration());
        preparedStatement.setInt(5, entity.getLanguage().getId());
        preparedStatement.setDate(6, new Date(entity.getFirstSeance().getTime()));
        preparedStatement.setDate(7, new Date(entity.getLastSeance().getTime()));
        preparedStatement.setString(8, entity.getSmallPoster().toString());
        preparedStatement.setString(9, entity.getBigPoster().toString());
        preparedStatement.setString(10, entity.getTrailerLink());
        preparedStatement.setInt(11, entity.getYear());
        preparedStatement.setInt(12, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<Film> readAll(ResultSet resultSet) throws SQLException {
        List<Film> result = new LinkedList<>();
        Film film;

        while (resultSet.next()) {
            film = new Film();
            film.setId(resultSet.getInt("id"));
            film.setTitle(resultSet.getString("title"));
            film.setDescribe(resultSet.getString("description"));
            film.setMinAge(resultSet.getInt("min_age"));
            film.setDuration(resultSet.getInt("duration"));
            film.setLanguage(daoFactory.getLanguageDao().getById(resultSet.getInt("film_language")));
            film.setFirstSeance(resultSet.getDate("first_seance"));
            film.setLastSeance(resultSet.getDate("last_seance"));
            film.setBigPoster(new File(resultSet.getString("big_poster")));
            film.setSmallPoster(new File(resultSet.getString("small_poster")));
            film.setTrailerLink(resultSet.getString("trailer_link"));
            film.setYear(resultSet.getInt("year"));

            result.add(film);
        }
        return result;
    }

}
