package ua.com.cinema1.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/admins", "/admin/users", "/admin/films", "/admin/seances",
        "/admin", "/admin/films/genres", "/admin/films/studios", "/admin/films/countries", "/admin/films/directors",
        "/admin/films/actors", "/admin/films/languages"})
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
                break;
            case "/admin/films":
                request.getRequestDispatcher("/WEB-INF/admin/films/films.jsp").forward(request, response);
                break;
            case "/admin/films/genres":
                request.getRequestDispatcher("/WEB-INF/admin/films/genres.jsp").forward(request, response);
                break;
            case "/admin/films/studios":
                request.getRequestDispatcher("/WEB-INF/admin/films/studios.jsp").forward(request, response);
                break;
            case "/admin/films/countries":
                request.getRequestDispatcher("/WEB-INF/admin/films/countries.jsp").forward(request, response);
                break;
            case "/admin/films/directors":
                request.getRequestDispatcher("/WEB-INF/admin/films/directors.jsp").forward(request, response);
                break;
            case "/admin/films/actors":
                request.getRequestDispatcher("/WEB-INF/admin/films/actors.jsp").forward(request, response);
                break;
            case "/admin/films/languages":
                request.getRequestDispatcher("/WEB-INF/admin/films/languages.jsp").forward(request, response);
                break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
