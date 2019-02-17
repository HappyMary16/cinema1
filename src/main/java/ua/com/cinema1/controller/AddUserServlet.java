package ua.com.cinema1.controller;

import ua.com.cinema1.dao.UserDao;
import ua.com.cinema1.model.Role;
import ua.com.cinema1.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddUserServlet", urlPatterns = {"/admin/add_user", "/admin/add_admin"})
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDao.getInstance();
        request.setCharacterEncoding("UTF-8");

        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setLogin(request.getParameter("login"));
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        String userPath = request.getServletPath();

        if (userPath.equals("/admin/add_user")) {
            user.setRole(Role.USER);
        } else {
            user.setRole(Role.ADMIN);
        }

        int id = userDao.insert(user);

        if (userPath.equals("/admin/add_user")) {
            response.sendRedirect("/admin/user?id=" + id);
        } else {
            response.sendRedirect("/admin/admin?id=" + id);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

