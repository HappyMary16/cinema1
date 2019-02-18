package ua.com.cinema1.controller.users;

import ua.com.cinema1.dao.UserDao;
import ua.com.cinema1.model.Role;
import ua.com.cinema1.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/admin/user_update"})
public class UpdateUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao userDao = UserDao.getInstance();
        request.setCharacterEncoding("UTF-8");

        User user = userDao.getById(Integer.valueOf(request.getParameter("id")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setLogin(request.getParameter("login"));
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        userDao.update(user);

        if (user.getRole() == Role.USER) {
            response.sendRedirect("/admin/user?id=" + user.getId());
        } else {
            response.sendRedirect("/admin/admin?id=" + user.getId());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
