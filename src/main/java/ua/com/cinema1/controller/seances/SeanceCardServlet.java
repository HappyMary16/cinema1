package ua.com.cinema1.controller.seances;

import ua.com.cinema1.dao.SeanceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SeanceCardServlet", urlPatterns = {"/admin/seances/new", "/admin/seance/delete", "/admin/seance", "/admin/seance/update"})
public class SeanceCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();

        switch (userPath) {
            case "/admin/seances/new":
                request.getRequestDispatcher("/WEB-INF/admin/seances/new_seance.jsp").forward(request, response);
                break;
            case "/admin/seance/update":
                request.getRequestDispatcher("/WEB-INF/admin/seances/seance_update.jsp").forward(request, response);
                break;
            case "/admin/seance":
                request.getRequestDispatcher("/WEB-INF/admin/seances/seance_card.jsp").forward(request, response);
                break;
            case "/admin/seance/delete":
                SeanceDao.getInstance().delete(Integer.valueOf(request.getParameter("id")));
                request.getRequestDispatcher("/WEB-INF/admin/seances/seances.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
