package ua.com.cinema1.dao;

import ua.com.cinema1.datasource.DataSource;
import ua.com.cinema1.model.Film;
import ua.com.cinema1.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GenresDao implements IGenreDao {

    private final String INSERT_FILM_GENRE = "INSERT INTO film_genre(film_id, genre_id) VALUES (?, ?)";
    private final String GET_FILM_GENRE = "SELECT * FROM film_genre WHERE film_id = ?";
    public static final String DELETE_FILM_GENRE = "DELETE FROM film_genre WHERE film_id = ?";

    private DataSource dataSource;

    private GenresDao() {
        this.dataSource = DataSource.getInstance();
    }

    public static GenresDao getInstance() {
        return new GenresDao();
    }

    @Override
    public void insertGenresByFilmId(Film film) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILM_GENRE)) {
            for (Genre g :
                    film.getGenres()) {
                preparedStatement.setInt(1, film.getId());
                preparedStatement.setInt(2, g.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Genre> getGenresByFilmId(int filmId) {
        List<Genre> result = new LinkedList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FILM_GENRE)) {

            preparedStatement.setInt(1, filmId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(Genre.getById(resultSet.getInt("genre_id")));
            }

            result.forEach(e -> System.out.println(e));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void deleteGenresByFilmId(int filmId) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILM_GENRE);) {
            preparedStatement.setInt(1, filmId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGenresByFilmId(Film film) {
        deleteGenresByFilmId(film.getId());
        insertGenresByFilmId(film);
    }

}
