package ua.com.cinema1.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/admins/new", "/admin/admins", "/admin/films", "/admin/halls", "/admin/seances", "/admin"})
public class AdminServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();

        switch (userPath) {
            case "/admin":
                request.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
                break;
            case "/admin/admins":
                request.getRequestDispatcher("/WEB-INF/admin/admins.jsp").forward(request, response);
            case "/admin/admins/new":
                request.getRequestDispatcher("/WEB-INF/admin/new_admin.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
