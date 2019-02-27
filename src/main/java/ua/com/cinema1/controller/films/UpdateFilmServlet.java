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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern p = Pattern.compile("\\((?<num>\\d+)\\)");

        List<Genre> genres = new LinkedList<>();
        Matcher m = p.matcher(request.getParameter("genres"));
        while (m.find()) {
            genres.add(Genre.getById(Integer.valueOf(m.group("num"))));
        }
        film.setGenres(genres);

        List<Studio> studios = new LinkedList<>();
        m = p.matcher(request.getParameter("studios"));
        while (m.find()) {
            studios.add(StudioDao.getInstance().getById(Integer.valueOf(m.group("num"))));
        }
        film.setStudios(studios);

        List<Country> countries = new LinkedList<>();
        m = p.matcher(request.getParameter("countries"));
        while (m.find()) {
            countries.add(CountryDao.getInstance().getById(Integer.valueOf(m.group("num"))));
        }
        film.setCountries(countries);

        List<Director> directors = new LinkedList<>();
        m = p.matcher(request.getParameter("directors"));
        while (m.find()) {
            directors.add(DirectorDao.getInstance().getById(Integer.valueOf(m.group("num"))));
        }
        film.setDirectors(directors);

        List<Actor> actors = new LinkedList<>();
        m = p.matcher(request.getParameter("actors"));
        while (m.find()) {
            actors.add(ActorDao.getInstance().getById(Integer.valueOf(m.group("num"))));
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
