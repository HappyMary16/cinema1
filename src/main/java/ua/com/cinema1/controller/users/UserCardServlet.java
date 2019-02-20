package ua.com.cinema1.controller.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserCardServlet", urlPatterns = {"/admin/user", "/admin/admin", "/admin/admins/update", "/admin/admins/new", "/admin/users/new"})
public class UserCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPath = request.getServletPath();

        switch (userPath) {

            case "/admin/admins/new":
                request.getRequestDispatcher("/WEB-INF/admin/users/new_admin.jsp").forward(request, response);
                break;
            case "/admin/users/new":
                request.getRequestDispatcher("/WEB-INF/admin/users/new_user.jsp").forward(request, response);
                break;
            case "/admin/user":
            case "/admin/admin":
                request.getRequestDispatcher("/WEB-INF/admin/users/user_card.jsp").forward(request, response);
                break;
            case "/admin/admins/update":
                request.getRequestDispatcher("/WEB-INF/admin/users/user_update.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
