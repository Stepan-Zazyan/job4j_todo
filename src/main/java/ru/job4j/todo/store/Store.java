package ru.job4j.todo.store;

import ru.job4j.todo.model.Task;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Store {

    Task add(Task task);

    boolean update(Integer id, Task task);

    boolean delete(Integer id);

    List<Task> findAll();

    Optional<Task> findById(Integer id);

    void close()  throws SQLException;
}
