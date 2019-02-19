package ua.com.cinema1.dao;

import ua.com.cinema1.model.Seance;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SeanceDao extends Dao<Seance> {

    private final String INSERT = "INSERT INTO seance (film_id, seance_date, seance_time, hall_id, price) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE seance SET film_id = ?, seance_date = ?, seance_time = ?, hall_id = ?, price = ? WHERE id = ?";

    private static SeanceDao seanceDao;

    private SeanceDao(Class<Seance> type) {
        super(type);
    }

    public static SeanceDao getInstance() {
        if (seanceDao == null) {
            seanceDao = new SeanceDao(Seance.class);
        }
        return seanceDao;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Seance entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        if (entity.getFilm().getId() == -1) {
            DaoFactory.getInstance().getFilmDao().insert(entity.getFilm());
        }
        if (entity.getHall().getId() == -1) {
            DaoFactory.getInstance().getHallDao().insert(entity.getHall());
        }
        preparedStatement.setInt(1, entity.getFilm().getId());
        preparedStatement.setDate(2, new java.sql.Date(entity.getDateAdnTime().getTime()));
        preparedStatement.setLong(3, entity.getDateAdnTime().getTime());
        preparedStatement.setInt(4, entity.getHall().getId());
        preparedStatement.setInt(5, entity.getPriceTicket());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Seance entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setInt(1, entity.getFilm().getId());
        preparedStatement.setDate(2, new java.sql.Date(entity.getDateAdnTime().getTime()));
        preparedStatement.setLong(3, entity.getDateAdnTime().getTime());
        preparedStatement.setInt(4, entity.getHall().getId());
        preparedStatement.setInt(5, entity.getPriceTicket());
        preparedStatement.setInt(6, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<Seance> readAll(ResultSet resultSet) throws SQLException {
        List<Seance> result = new LinkedList<>();

        while (resultSet.next()) {
            Seance entity = new Seance();
            entity.setId(resultSet.getInt("id"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-DD");

            Date date = null;
            try {
                date = dateFormat.parse(resultSet
                        .getDate("seance_date").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(date);
            date.setTime(resultSet.getLong("seance_time"));
            entity.setDateAdnTime(date);

            entity.setFilm(FilmDao.getInstance().getById(resultSet.getInt("film_id")));
            entity.setHall(HallDao.getInstance().getById(resultSet.getInt("hall_id")));
            entity.setPriceTicket(resultSet.getInt("price"));
            result.add(entity);
        }
        return result;
    }
}