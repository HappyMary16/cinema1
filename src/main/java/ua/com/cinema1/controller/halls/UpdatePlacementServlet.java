package ua.com.cinema1.controller.halls;

import ua.com.cinema1.dao.DaoFactory;
import ua.com.cinema1.dao.HallDao;
import ua.com.cinema1.model.Hall;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdatePlacementServlet", urlPatterns = {"/admin/update_placement"})
public class UpdatePlacementServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HallDao hallDao = (HallDao) DaoFactory.getInstance().getHallDao();
        Hall hall = hallDao.getById(Integer.valueOf(request.getParameter("id")));

        boolean[][] placement = new boolean[hall.getHeight()][hall.getWidth()];
        for (int i = 0; i < hall.getHeight(); i++) {
            for (int j = 0; j < hall.getWidth(); j++) {
                placement[i][j] = request.getParameter(i + "a" + j).equals("1");
            }
        }

        hall.setPlacement(placement);

        hallDao.update(hall);
        response.sendRedirect("/admin/hall?id=" + hall.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}

