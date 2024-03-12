package org.example.spring.dao;

import org.example.spring.models.Person;

import java.util.List;

public interface Action<T> {
    List<T> index();
    T show (int id);
    void save(T person);
    void update(int id, T person);
    void delete(int id);
}
