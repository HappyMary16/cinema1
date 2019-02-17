package ua.com.cinema1.service;

import java.util.List;

public interface IServise<K, V> {
    List<V> getAll();

    K create(V value);

    V read(K key);

    void update(V value);

    void delete(K key);
}
