package ua.com.cinema1.controller.films;

import ua.com.cinema1.dao.FilmDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FilmCardServlet", urlPatterns = {"/admin/films/new", "/admin/film/delete", "/admin/film", "/admin/film/update"})
public class FilmCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();

        switch (userPath) {
            case "/admin/films/new":
                request.getRequestDispatcher("/WEB-INF/admin/films/new_film.jsp").forward(request, response);
                break;
            case "/admin/film/update":
                request.getRequestDispatcher("/WEB-INF/admin/films/film_update.jsp").forward(request, response);
                break;
            case "/admin/film":
                request.getRequestDispatcher("/WEB-INF/admin/films/film_card.jsp").forward(request, response);
                break;
            case "/admin/film/delete":
                FilmDao.getInstance().delete(Integer.valueOf(request.getParameter("id")));
                request.getRequestDispatcher("/WEB-INF/admin/films/films.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
