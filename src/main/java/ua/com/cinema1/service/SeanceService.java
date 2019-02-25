package ua.com.cinema1.service;

import ua.com.cinema1.dao.SeanceDao;
import ua.com.cinema1.model.Seance;

import java.util.List;

public class SeanceService implements IServise<Integer, Seance> {

    private static final SeanceDao dao = SeanceDao.getInstance();
    private static final SeanceService service = new SeanceService();

    private SeanceService() {
    }

    public static SeanceService getInstance() {
        return service;
    }

    @Override
    public List<Seance> getAll() {
        return dao.getAll();
    }

    @Override
    public Integer create(Seance value) {
        return dao.insert(value);
    }

    @Override
    public Seance getById(Integer key) {
        return dao.getById(key);
    }

    @Override
    public void update(Seance value) {
        dao.update(value);
    }

    @Override
    public void delete(Integer key) {
        dao.delete(key);
    }

    public List<Seance> getAllBy(String column, String value) {
        return dao.getAllByValue(column, value);
    }
}
