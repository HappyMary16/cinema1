package ua.com.cinema1.controller.films;

import ua.com.cinema1.dao.FilmDao;
import ua.com.cinema1.model.Film;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateFilmServlet", urlPatterns = {"/admin/seance_update"})
public class UpdateFilmServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmDao filmDao = FilmDao.getInstance();
        request.setCharacterEncoding("UTF-8");

        Film film = filmDao.getById(Integer.valueOf(request.getParameter("id")));
        film.setTitle(request.getParameter("title"));
        film.setDescribe(request.getParameter("description"));
        film.setMinAge(Integer.valueOf(request.getParameter("age")));
        film.setMinAge(Integer.valueOf(request.getParameter("age")));
        film.setDuration(Integer.parseInt(request.getParameter("duration")));
//        film.setLanguage();
//        film.setFirstSeance();
//        film.setLastSeance();
//        film.setSmallPoster();
//        film.setBigPoster();
//        film.setTrailerLink();

        response.sendRedirect("/admin/seance?id=" + film.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
