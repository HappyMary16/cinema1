package ua.com.cinema1.controller.seances;

import ua.com.cinema1.dao.FilmDao;
import ua.com.cinema1.dao.HallDao;
import ua.com.cinema1.model.Film;
import ua.com.cinema1.model.Hall;
import ua.com.cinema1.model.Seance;
import ua.com.cinema1.service.SeanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddSeanceServlet", urlPatterns = {"/admin/add_seance"})
public class AddSeanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SeanceService seanceService = SeanceService.getInstance();
        request.setCharacterEncoding("UTF-8");

        Film film = FilmDao.getInstance().getById(Integer.valueOf(request.getParameter("film")));
        Hall hall = HallDao.getInstance().getById(Integer.valueOf(request.getParameter("hall")));
        int price = Integer.parseInt(request.getParameter("price"));
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
            dateFrom.setTime(new SimpleDateFormat("HH:mm")
                    .parse(request.getParameter("time"))
                    .getTime() + dateFrom.getTime() + 2L * 60 * 60 * 1000);
            dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
            dateTo.setTime(new SimpleDateFormat("HH:mm")
                    .parse(request.getParameter("time"))
                    .getTime() + dateTo.getTime() + 2L * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Date date = dateFrom; date.before(dateTo) || date.equals(dateTo); date.setTime(date.getTime() + 24L * 60 * 60 * 1000)) {
            Seance seance = new Seance();
            seance.setFilm(film);
            seance.setHall(hall);
            seance.setDateAdnTime(date);
            seance.setPriceTicket(price);
            boolean check = true;

            long dateEnd = date.getTime() + (film.getDuration() + 10) * 60 * 1000;
            for (Seance s :
                    seanceService.getAllBy("hall_id", String.valueOf(seance.getHall().getId()))) {
                long sFilmEnd = s.getDateAdnTime().getTime() + (s.getFilm().getDuration() + 10) * 60 * 1000;
                if ((date.getTime() > s.getDateAdnTime().getTime() && date.getTime() < sFilmEnd)
                        || (dateEnd > s.getDateAdnTime().getTime() && dateEnd < sFilmEnd)) {
                    check = false;
                }
            }

            if (check) {
                seanceService.create(seance);
            }
        }

        response.sendRedirect("/admin/seances");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

