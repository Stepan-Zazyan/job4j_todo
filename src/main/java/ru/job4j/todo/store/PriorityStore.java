package ru.job4j.todo.store;

import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;

import java.sql.SQLException;
import java.util.Optional;

public interface PriorityStore {
    Optional<Priority> findById(Integer id);

    void close()  throws SQLException;
}
