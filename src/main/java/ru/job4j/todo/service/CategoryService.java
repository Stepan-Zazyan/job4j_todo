package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {

    List<Category> findAll();

}
