package ua.com.cinema1.controller.films;

import ua.com.cinema1.dao.LanguagesDao;
import ua.com.cinema1.model.Language;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddLanguageServlet", urlPatterns = {"/admin/add_language"})
public class AddLanguageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LanguagesDao languagesDao = LanguagesDao.getInstance();
        req.setCharacterEncoding("UTF-8");

        Language language = new Language();
        language.setLanguage(req.getParameter("languageAdd"));

        if (!languagesDao.getAll().contains(language)) {
            languagesDao.insert(language);
        }

    }
}
