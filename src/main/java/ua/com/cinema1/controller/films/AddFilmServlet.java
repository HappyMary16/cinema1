package ua.com.cinema1.controller.films;

import ua.com.cinema1.dao.DaoFactory;
import ua.com.cinema1.model.Film;
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

@WebServlet(name = "AddFilmServlet", urlPatterns = {"/admin/add_film"})
public class AddFilmServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FilmService filmService = FilmService.getInstance();
        request.setCharacterEncoding("UTF-8");
        Film film = new Film();

        film.setTitle(request.getParameter("title"));
        film.setDescribe(request.getParameter("description"));
        film.setMinAge(Integer.valueOf(request.getParameter("minAge")));
        film.setDuration(Integer.parseInt(request.getParameter("duration")));
        film.setLanguage(DaoFactory
                .getInstance()
                .getLanguageDao()
                .getById(Integer.valueOf(request.getParameter("language"))));

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

        response.sendRedirect("/admin/film?id=" + filmService.create(film));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

