package ua.com.cinema1.dao;

public interface IPlacementDao {

    void getAll(boolean[][] places, int hallId);
    void insertAll(boolean[][] places, int hallId);
    void deleteAll(int hallId);
    void updateAll(boolean[][] places, int hallId);
}
