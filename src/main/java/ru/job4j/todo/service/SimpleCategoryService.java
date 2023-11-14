package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.CategoryStore;

import java.util.List;
@Service
public class SimpleCategoryService {

    private  final CategoryStore categoryStore;

    public SimpleCategoryService(CategoryStore categoryStore) {
        this.categoryStore = categoryStore;
    }

    List<Category> findAll() {
        return categoryStore.findAll();
    }
}
