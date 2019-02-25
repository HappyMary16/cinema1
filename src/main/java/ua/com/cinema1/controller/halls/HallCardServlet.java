package ua.com.cinema1.controller.halls;

import ua.com.cinema1.dao.FilmDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HallCardServlet", urlPatterns = {"/admin/halls/new", "/admin/hall/delete", "/admin/hall", "/admin/hall/update"})
public class HallCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();

        switch (userPath) {
            case "/admin/halls/new":
                request.getRequestDispatcher("/WEB-INF/admin/halls/new_hall.jsp").forward(request, response);
                break;
            case "/admin/hall/update":
                request.getRequestDispatcher("/WEB-INF/admin/halls/hall_update.jsp").forward(request, response);
                break;
            case "/admin/hall":
                request.getRequestDispatcher("/WEB-INF/admin/halls/hall_card.jsp").forward(request, response);
                break;
            case "/admin/hall/delete":
                FilmDao.getInstance().delete(Integer.valueOf(request.getParameter("id")));
                request.getRequestDispatcher("/WEB-INF/admin/halls/halls.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
