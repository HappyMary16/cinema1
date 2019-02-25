package ua.com.cinema1.controller.films;

import ua.com.cinema1.dao.*;
import ua.com.cinema1.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddFilmDataServlet", urlPatterns = {"/admin/films/languages/add", "/admin/films/studios/add",
        "/admin/films/countries/add", "/admin/films/directors/add", "/admin/films/actors/add"})
public class AddFilmDataServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String userPath = req.getServletPath();

        switch (userPath) {
            case "/admin/films/languages/add":
                IDao<Integer, Language> languagesDao = DaoFactory.getInstance().getLanguageDao();
                Language language = new Language();
                language.setLanguage(req.getParameter("language"));
                if (!languagesDao.getAll().contains(language)) {
                    languagesDao.insert(language);
                }
                req.getRequestDispatcher("/WEB-INF/admin/films/languages.jsp").forward(req, resp);
                break;
            case "/admin/films/studios/add":
                IDao<Integer, Studio> studioDao = StudioDao.getInstance();
                Studio studio = new Studio();
                studio.setStudio(req.getParameter("studio"));
                if (!studioDao.getAll().contains(studio)) {
                    studioDao.insert(studio);
                }
                req.getRequestDispatcher("/WEB-INF/admin/films/studios.jsp").forward(req, resp);
                break;
            case "/admin/films/countries/add":
                IDao<Integer, Country> countryDao = CountryDao.getInstance();
                Country country = new Country();
                country.setCountry(req.getParameter("country"));
                if (!countryDao.getAll().contains(country)) {
                    countryDao.insert(country);
                }
                req.getRequestDispatcher("/WEB-INF/admin/films/countries.jsp").forward(req, resp);
                break;
            case "/admin/films/directors/add":
                IDao<Integer, Director> directorDao = DirectorDao.getInstance();
                Director director = new Director();
                director.setFirstName(req.getParameter("firstName"));
                director.setLastName(req.getParameter("lastName"));
                if (!directorDao.getAll().contains(director)) {
                    directorDao.insert(director);
                }
                req.getRequestDispatcher("/WEB-INF/admin/films/directors.jsp").forward(req, resp);
                break;
            case "/admin/films/actors/add":
                IDao<Integer, Actor> actorDao = ActorDao.getInstance();
                Actor actor = new Actor();
                actor.setFirstName(req.getParameter("firstName"));
                actor.setLastName(req.getParameter("lastName"));
                if (!actorDao.getAll().contains(actor)) {
                    actorDao.insert(actor);
                }
                req.getRequestDispatcher("/WEB-INF/admin/films/actors.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
