package ua.com.cinema1.controller.halls;

import ua.com.cinema1.dao.HallDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HallCardServlet", urlPatterns = {"/admin/halls/new", "/admin/hall/delete", "/admin/hall"
        , "/admin/hall/update", "/admin/add_hall_placement", "/admin/redact_placement"})
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
                HallDao.getInstance().delete(Integer.valueOf(request.getParameter("id")));
                request.getRequestDispatcher("/WEB-INF/admin/halls/halls.jsp").forward(request, response);
                break;
            case "/admin/add_hall_placement":
                request.getRequestDispatcher("/WEB-INF/admin/halls/add_placement.jsp").forward(request, response);
                break;
            case "/admin/redact_placement":
                request.getRequestDispatcher("/WEB-INF/admin/halls/update_placement.jsp").forward(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
