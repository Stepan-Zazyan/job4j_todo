package ru.job4j.todo.service;

import ru.job4j.todo.dto.TaskDto;
import ru.job4j.todo.model.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task add(Task task);

    boolean update(Task task);

    boolean updateDone(Task task);

    boolean delete(Integer id);

    List<TaskDto> findAll();

    Optional<Task> findById(Integer id);

    void close()  throws SQLException;
}
