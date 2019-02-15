package ua.com.cinema1.dao;

import ua.com.cinema1.model.Place;
import ua.com.cinema1.model.Ticket;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TicketDao extends Dao<Ticket>{

    private final String INSERT = "INSERT INTO ticket (seance_id, user_id, row_num, place) VALUES (?, ?, ?, ?)";
    private final String UPDATE = "UPDATE ticket SET seance_id = ?, user_id = ?, row_num = ?, place = ? WHERE id = ?";

    private static TicketDao ticketDao;

    private TicketDao(Class<Ticket> type) {
        super(type);
    }

    public static TicketDao getInstance() {
        if (ticketDao == null) {
            ticketDao = new TicketDao(Ticket.class);
        }
        return ticketDao;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Ticket entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        if (entity.getSeance().getId() == -1) {
            DaoFactory.getInstance().getSeanceDao().insert(entity.getSeance());
        }
        if (entity.getUser().getId() == -1) {
            DaoFactory.getInstance().getUserDao().insert(entity.getUser());
        }
        preparedStatement.setInt(1, entity.getSeance().getId());
        preparedStatement.setInt(2, entity.getUser().getId());
        preparedStatement.setInt(3, entity.getPlace().getRow());
        preparedStatement.setInt(4, entity.getPlace().getNumber());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Ticket entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setInt(1, entity.getSeance().getId());
        preparedStatement.setInt(2, entity.getUser().getId());
        preparedStatement.setInt(3, entity.getPlace().getRow());
        preparedStatement.setInt(4, entity.getPlace().getNumber());
        preparedStatement.setInt(5, entity.getId());

        return preparedStatement;
    }

    @Override
    protected List<Ticket> readAll(ResultSet resultSet) throws SQLException {
        List<Ticket> result = new LinkedList<>();

        while (resultSet.next()) {
            Ticket entity = new Ticket();
            entity.setId(resultSet.getInt("id"));
            entity.setSeance(SeanceDao.getInstance().getById(resultSet.getInt("seance_id")));
            entity.setUser(UserDao.getInstance().getById(resultSet.getInt("user_id")));
            entity.setPlace(new Place(resultSet.getInt("row"), resultSet.getInt("place")));
            result.add(entity);
        }
        return result;
    }
}