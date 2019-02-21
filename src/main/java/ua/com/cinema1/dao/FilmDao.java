package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.*;

import java.io.File;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FilmDao extends Dao<Film>{

    private final String INSERT = "INSERT INTO film (title, description, min_age, duration, film_language, first_seance, last_seance, small_poster, big_poster, trailer_link) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE film SET title = ?, description = ?, min_age = ?, duration = ?, film_language = ?, first_seance = ?, last_seance = ?, small_poster = ?, big_poster = ?, trailer_link = ? WHERE id = ?";

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
        //preparedStatement.setString(5, entity.getLanguage());
        preparedStatement.setDate(6, new Date(entity.getFirstSeance().getTime()));
        preparedStatement.setDate(7, new Date(entity.getLastSeance().getTime()));
        preparedStatement.setString(8, entity.getSmallPoster().toString());
        preparedStatement.setString(9, entity.getBigPoster().toString());
        preparedStatement.setString(10, entity.getTrailerLink());

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

            daoFactory.getGenresDao().insertGenresByFilmId(entity);
            daoFactory.getActorDao().insertDataByFilmId(entity.getActors(), entity.getId(), ActorDao.getInstance());
            daoFactory.getCountryDao().insertDataByFilmId(entity.getCountries(), entity.getId(), CountryDao.getInstance());
            daoFactory.getDirectorDao().insertDataByFilmId(entity.getDirectors(), entity.getId(), DirectorDao.getInstance());
            daoFactory.getStudioDao().insertDataByFilmId(entity.getStudios(), entity.getId(), StudioDao.getInstance());

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
        preparedStatement.setString(5, entity.getLanguage().getLanguage());
        preparedStatement.setDate(6, new Date(entity.getFirstSeance().getTime()));
        preparedStatement.setDate(7, new Date(entity.getLastSeance().getTime()));
        preparedStatement.setString(8, entity.getSmallPoster().toString());
        preparedStatement.setString(9, entity.getBigPoster().toString());
        preparedStatement.setString(10, entity.getTrailerLink());
        preparedStatement.setInt(11, entity.getId());

        daoFactory.getGenresDao().updateGenresByFilmId(entity);
        daoFactory.getStudioDao().updateAllByFilmId(entity.getId(), entity.getStudios(), StudioDao.getInstance());
        daoFactory.getDirectorDao().updateAllByFilmId(entity.getId(), entity.getDirectors(), DirectorDao.getInstance());
        daoFactory.getActorDao().updateAllByFilmId(entity.getId(), entity.getActors(), ActorDao.getInstance());
        daoFactory.getCountryDao().updateAllByFilmId(entity.getId(), entity.getCountries(), CountryDao.getInstance());

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
            //film.setLanguage(resultSet.getString("film_language"));
            film.setFirstSeance(resultSet.getDate("first_seance"));
            film.setLastSeance(resultSet.getDate("last_seance"));
            film.setBigPoster(new File(resultSet.getString("big_poster")));
            film.setSmallPoster(new File(resultSet.getString("small_poster")));
            film.setTrailerLink(resultSet.getString("trailer_link"));
            film.setGenres(daoFactory.getGenresDao().getGenresByFilmId(film.getId()));
            film.setCountries(daoFactory
                    .getCountryDao()
                    .getAllByFilmId(film.getId(), CountryDao.getInstance()));
            film.setStudios(daoFactory
                    .getStudioDao()
                    .getAllByFilmId(film.getId(), StudioDao.getInstance()));
            film.setActors(daoFactory
                    .getActorDao()
                    .getAllByFilmId(film.getId(), ActorDao.getInstance()));
            film.setDirectors(daoFactory
                    .getDirectorDao()
                    .getAllByFilmId(film.getId(), DirectorDao.getInstance()));
            result.add(film);
        }
        return result;
    }

}
