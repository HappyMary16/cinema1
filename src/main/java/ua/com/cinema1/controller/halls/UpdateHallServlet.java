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

@WebServlet(name = "UpdateHallServlet", urlPatterns = {"/admin/update_hall"})
public class UpdateHallServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HallDao hallDao = (HallDao) DaoFactory.getInstance().getHallDao();
        Hall hall = hallDao.getById(Integer.valueOf(request.getParameter("id")));
        hall.setName(request.getParameter("name"));
        hall.setHeight(Integer.valueOf(request.getParameter("height")));
        hall.setWidth(Integer.valueOf(request.getParameter("width")));

        boolean[][] placement = new boolean[hall.getHeight()][hall.getWidth()];
        int h = hall.getHeight() > hall.getPlacement().length ? hall.getPlacement().length : hall.getHeight();
        int w = hall.getWidth() > hall.getPlacement()[0].length ? hall.getPlacement()[0].length : hall.getWidth();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                placement[i][j] = hall.getPlacement()[i][j];
            }
        }

        hall.setPlacement(placement);

        hallDao.update(hall);
        request.getRequestDispatcher("/WEB-INF/admin/halls/hall_update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

