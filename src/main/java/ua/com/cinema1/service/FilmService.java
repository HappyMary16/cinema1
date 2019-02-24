package ua.com.cinema1.service;

import ua.com.cinema1.dao.*;
import ua.com.cinema1.model.Film;

import java.util.List;

public class FilmService implements IServise<Integer, Film> {

    private static FilmService instance = new FilmService();
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    private static IDao<Integer, Film> filmDao = daoFactory.getFilmDao();

    private FilmService() {
    }

    public static FilmService getInstance() {
        return instance;
    }

    @Override
    public List<Film> getAll() {
        List<Film> films = filmDao.getAll();

        for (Film film :
                films) {
            setFilmData(film);
        }

        return films;
    }

    @Override
    public Integer create(Film entity) {
        int id = filmDao.insert(entity);

        daoFactory.getGenresDao().insertGenresByFilmId(entity);
        daoFactory.getActorDao().insertDataByFilmId(entity.getActors(), entity.getId(), ActorDao.getInstance());
        daoFactory.getCountryDao().insertDataByFilmId(entity.getCountries(), entity.getId(), CountryDao.getInstance());
        daoFactory.getDirectorDao().insertDataByFilmId(entity.getDirectors(), entity.getId(), DirectorDao.getInstance());
        daoFactory.getStudioDao().insertDataByFilmId(entity.getStudios(), entity.getId(), StudioDao.getInstance());

        return id;
    }

    @Override
    public Film getById(Integer key) {
        Film film = filmDao.getById(key);
        setFilmData(film);
        return film;
    }

    private void setFilmData(Film film) {
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
    }

    @Override
    public void update(Film entity) {
        filmDao.update(entity);

        daoFactory.getGenresDao().updateGenresByFilmId(entity);
        daoFactory.getStudioDao().updateAllByFilmId(entity.getId(), entity.getStudios(), StudioDao.getInstance());
        daoFactory.getDirectorDao().updateAllByFilmId(entity.getId(), entity.getDirectors(), DirectorDao.getInstance());
        daoFactory.getActorDao().updateAllByFilmId(entity.getId(), entity.getActors(), ActorDao.getInstance());
        daoFactory.getCountryDao().updateAllByFilmId(entity.getId(), entity.getCountries(), CountryDao.getInstance());
    }

    @Override
    public void delete(Integer key) {
        filmDao.delete(key);
    }
}
