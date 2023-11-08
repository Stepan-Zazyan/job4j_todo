package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.dto.TaskDto;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.SimpleTaskStore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleTaskService implements TaskService {

    private final SimpleTaskStore taskStore;

    private final SimpleUserService userService;

    private final SimplePriorityService priorityService;

    public SimpleTaskService(SimpleTaskStore taskStore,
                             SimpleUserService userService,
                             SimplePriorityService priorityService) {
        this.taskStore = taskStore;
        this.userService = userService;
        this.priorityService = priorityService;
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
    public List<TaskDto> findAll() {
        Collection<Task> taskCollection = taskStore.findAll();
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task: taskCollection) {
            User user = userService.findById(task.getId()).get();
            Priority priority = priorityService.findById(task.getPriority().getPosition()).get();
            taskDtoList.add(new TaskDto(task.getId(), task.getTitle(), task.getDescription(),
                    task.getCreated(), task.getDone(), user.getName(), priority.getName()));
        }
        return taskDtoList;
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
