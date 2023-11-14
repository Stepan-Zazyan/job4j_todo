package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.SimpleUserStore;

import java.util.*;

@Service
public class SimpleUserService implements UserService {

    private final SimpleUserStore userStore;

    public SimpleUserService(SimpleUserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public Optional<User> save(User user) {
        return userStore.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userStore.findByEmailAndPassword(email, password);
    }

    @Override
    public Collection<User> findAll() {
        return userStore.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        return userStore.deleteById(id);
    }

    @Override
    public Optional<User> findById(int id) {
        return userStore.findById(id);
    }
}
