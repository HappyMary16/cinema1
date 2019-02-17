package ua.com.cinema1.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserCardServlet", urlPatterns = {"/admin/user", "/admin/admin"})
public class UserCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/admin/user")) {
            request.getRequestDispatcher("/WEB-INF/admin/usercard.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/admin/admincard.jsp").forward(request, response);
        }
    }
}
