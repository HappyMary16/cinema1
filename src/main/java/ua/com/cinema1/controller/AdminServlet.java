package ua.com.cinema1.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/admins", "/admin/users", "/admin/films", "/admin/seances", "/admin"})
public class AdminServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();

        switch (userPath) {
            case "/admin":
                request.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
                break;
            case "/admin/admins":
                request.getRequestDispatcher("/WEB-INF/admin/users/admins.jsp").forward(request, response);
                break;
            case "/admin/users":
                request.getRequestDispatcher("/WEB-INF/admin/users/users.jsp").forward(request, response);
                break;
            case "/admin/seances":
                request.getRequestDispatcher("/WEB-INF/admin/seances/seances.jsp").forward(request, response);
            case "/admin/films":
                request.getRequestDispatcher("/WEB-INF/admin/films/films.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
