package ua.com.cinema1.controller.users;

import ua.com.cinema1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", urlPatterns = {"/admin/admins/delete", "/admin/users/delete"})
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService.getInstance().delete(Integer.valueOf(request.getParameter("id")));

        switch (request.getServletPath()) {
            case "/admin/admins/delete":
                request.getRequestDispatcher("/WEB-INF/admin/users/admins.jsp").forward(request, response);
            case "/admin/users/delete":
                request.getRequestDispatcher("/WEB-INF/admin/users/users.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

