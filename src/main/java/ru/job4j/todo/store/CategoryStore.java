package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryStore {

    List<Category> findAll();

    void close()  throws SQLException;

}
