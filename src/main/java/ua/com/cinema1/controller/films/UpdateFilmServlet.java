package ua.com.cinema1.controller.films;

import ua.com.cinema1.dao.*;
import ua.com.cinema1.model.*;
import ua.com.cinema1.service.FilmService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "UpdateFilmServlet", urlPatterns = {"/admin/film_update"})
public class UpdateFilmServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmService filmService = FilmService.getInstance();
        request.setCharacterEncoding("UTF-8");
        Film film = filmService.getById(Integer.valueOf(request.getParameter("id")));

        film.setTitle(request.getParameter("title"));
        film.setDescribe(request.getParameter("description"));
        film.setMinAge(Integer.valueOf(request.getParameter("minAge")));
        film.setDuration(Integer.parseInt(request.getParameter("duration")));
        film.setLanguage(DaoFactory
                .getInstance()
                .getLanguageDao()
                .getById(Integer.valueOf(request.getParameter("language"))));
        film.setYear(Integer.parseInt(request.getParameter("year")));

        List<Genre> genres = new LinkedList<>();
        for (String s :
                request.getParameter("genres").split("\\D")) {
            if (!s.isEmpty()) {
                genres.add(Genre.getById(Integer.valueOf(s)));
            }
        }
        film.setGenres(genres);

        List<Studio> studios = new LinkedList<>();
        for (String s :
                request.getParameter("studios").split("\\D")) {
            if (!s.isEmpty()) {
                studios.add(StudioDao.getInstance().getById(Integer.valueOf(s)));
            }
        }
        film.setStudios(studios);

        List<Country> countries = new LinkedList<>();
        for (String s :
                request.getParameter("countries").split("\\D")) {
            if (!s.isEmpty()) {
                countries.add(CountryDao.getInstance().getById(Integer.valueOf(s)));
            }
        }
        film.setCountries(countries);

        List<Director> directors = new LinkedList<>();
        for (String s :
                request.getParameter("directors").split("\\D")) {
            if (!s.isEmpty()) {
                directors.add(DirectorDao.getInstance().getById(Integer.valueOf(s)));
            }
        }
        film.setDirectors(directors);

        List<Actor> actors = new LinkedList<>();
        for (String s :
                request.getParameter("actors").split("\\D")) {
            if (!s.isEmpty()) {
                actors.add(ActorDao.getInstance().getById(Integer.valueOf(s)));
            }
        }
        film.setActors(actors);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("firstSeance"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        film.setFirstSeance(date);
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("lastSeance"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        film.setLastSeance(date);

        film.setSmallPoster(new File(request.getParameter("smallPoster")));
        film.setBigPoster(new File(request.getParameter("bigPoster")));
        film.setTrailerLink(request.getParameter("trailer"));

        filmService.update(film);
        response.sendRedirect("/admin/film?id=" + film.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
