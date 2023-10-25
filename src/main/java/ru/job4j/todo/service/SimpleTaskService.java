package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.SimpleTaskStore;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleTaskService implements TaskService {

    private final SimpleTaskStore taskStore;

    public SimpleTaskService(SimpleTaskStore taskStore) {
        this.taskStore = taskStore;
    }

    @Override
    public Task add(Task task) {
        return taskStore.add(task);
    }

    @Override
    public boolean update(Task task) {
        return taskStore.update(task);
    }

    @Override
    public boolean updateDone(Task task) {
        return taskStore.updateDone(task);
    }

    @Override
    public boolean delete(Integer id) {
        return taskStore.delete(id);
    }

    @Override
    public List<Task> findAll() {
        return taskStore.findAll();
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return taskStore.findById(id);
    }

    @Override
    public void close() throws SQLException {
        taskStore.close();
    }
}
