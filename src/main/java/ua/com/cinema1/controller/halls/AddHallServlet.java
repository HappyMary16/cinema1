package ua.com.cinema1.controller.halls;

import ua.com.cinema1.dao.DaoFactory;
import ua.com.cinema1.dao.HallDao;
import ua.com.cinema1.model.Hall;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddHallServlet", urlPatterns = {"/admin/add_hall"})
public class AddHallServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HallDao hallDao = (HallDao) DaoFactory.getInstance().getHallDao();
        Hall hall = new Hall();
        hall.setName(request.getParameter("name"));
        System.out.println(hall.getName());
        hall.setHeight(Integer.valueOf(request.getParameter("height")));
        hall.setWidth(Integer.valueOf(request.getParameter("width")));

        boolean[][] placement = new boolean[hall.getHeight()][hall.getWidth()];
        System.out.println(request.getParameter("h" + 0 + " " + 0));
        for (int i = 0; i < hall.getHeight(); i++) {
            for (int j = 0; j < hall.getWidth(); j++) {
                placement[i][j] = request.getParameter(i + "a" + j).equals("1");
            }
        }

        hall.setPlacement(placement);

        response.sendRedirect("/admin/hall?id=" + hallDao.insert(hall));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

