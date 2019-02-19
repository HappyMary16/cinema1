package ua.com.cinema1.controller.seances;

import ua.com.cinema1.dao.FilmDao;
import ua.com.cinema1.dao.HallDao;
import ua.com.cinema1.dao.SeanceDao;
import ua.com.cinema1.model.Seance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UpdateSeanceServlet", urlPatterns = {"/admin/seance_update"})
public class UpdateSeanceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SeanceDao seanceDao = SeanceDao.getInstance();
        request.setCharacterEncoding("UTF-8");

        Seance seance = seanceDao.getById(Integer.valueOf(request.getParameter("id")));
        seance.setFilm(FilmDao.getInstance().getById(Integer.valueOf(request.getParameter("film"))));
        seance.setHall(HallDao.getInstance().getById(Integer.valueOf(request.getParameter("hall"))));
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
            date.setTime(new SimpleDateFormat("HH:mm").parse(request.getParameter("time")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        seance.setDateAdnTime(date);
        seance.setPriceTicket(Integer.parseInt(request.getParameter("price")));
        seanceDao.update(seance);

        response.sendRedirect("/admin/seance?id=" + seance.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
