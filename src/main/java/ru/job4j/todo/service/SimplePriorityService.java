package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.store.SimplePriorityStore;

import java.util.Optional;

@Service
public class SimplePriorityService implements PriorityService {

    private final SimplePriorityStore priorityStore;

    public SimplePriorityService(SimplePriorityStore priorityStore) {
        this.priorityStore = priorityStore;
    }

    @Override
    public Optional<Priority> findById(Integer id) {
        return priorityStore.findById(id);
    }
}
