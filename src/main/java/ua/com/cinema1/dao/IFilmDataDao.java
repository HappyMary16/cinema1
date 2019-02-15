package ua.com.cinema1.dao;

import ua.com.cinema1.model.Entity;

import java.util.List;

public interface IFilmDataDao<T extends Entity> {

    void insertDataByFilmId(List<T> data, int filmId, Dao<T> dao);

    List<T> getAllByFilmId(int filmId, Dao<T> dao);

    void deleteAllByFilmId(int filmId);

    void updateAllByFilmId(int filmId, List<T> newData, Dao<T> dao);
}
