package ua.com.cinema1.dao;

import ua.com.cinema1.model.*;

public class DaoFactory {

    private static DaoFactory daoFactory = null;

    private FilmDao filmDao;
    private IDao<Integer, Cinema> cinemaDao;
    private IDao<Integer, Hall> hallDao;
    private IDao<Integer, Ticket> ticketDao;
    private IDao<Integer, Seance> seanceDao;
    private IDao<Integer, User> userDao;
    private IGenreDao genresDao;
    private IFilmDataDao<Country> countryDao;
    private IFilmDataDao<Director> directorDao;
    private IFilmDataDao<Actor> actorDao;
    private IFilmDataDao<Studio> studioDao;


    private DaoFactory() {
        loadDaos();
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    private void loadDaos() {
        filmDao = FilmDao.getInstance();
        cinemaDao = CinemaDao.getInstance();
        hallDao = HallDao.getInstance();
        ticketDao = TicketDao.getInstance();
        seanceDao = SeanceDao.getInstance();
        userDao = UserDao.getInstance();
        genresDao = GenresDao.getInstance();
        countryDao = new FilmDataDao<>(Country.class);
        directorDao = new FilmDataDao<>(Director.class);
        actorDao = new FilmDataDao<>(Actor.class);
        studioDao = new FilmDataDao<>(Studio.class);
    }

    public IDao<Integer, Film> getFilmDao() {
        return filmDao;
    }

    public IFilmDataDao<Actor> getActorDao() {
        return actorDao;
    }

    public IDao<Integer, Cinema> getCinemaDao() {
        return cinemaDao;
    }

    public IFilmDataDao<Country> getCountryDao() {
        return countryDao;
    }

    public IFilmDataDao<Director> getDirectorDao() {
        return directorDao;
    }

    public IDao<Integer, Hall> getHallDao() {
        return hallDao;
    }

    public IDao<Integer, Ticket> getTicketDao() {
        return ticketDao;
    }

    public IDao<Integer, Seance> getSeanceDao() {
        return seanceDao;
    }

    public IDao<Integer, User> getUserDao() {
        return userDao;
    }

    public IGenreDao getGenresDao() {
        return genresDao;
    }

    public IFilmDataDao<Studio> getStudioDao() {
        return studioDao;
    }
}
