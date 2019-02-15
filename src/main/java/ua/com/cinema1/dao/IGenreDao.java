package ua.com.cinema1.dao;

import ua.com.cinema1.model.Film;
import ua.com.cinema1.model.Genre;

import java.util.List;

public interface IGenreDao {

    void insertGenresByFilmId(Film film);

    List<Genre> getGenresByFilmId(int filmId);

    void deleteGenresByFilmId(int filmId);

    void updateGenresByFilmId(Film film);
}
