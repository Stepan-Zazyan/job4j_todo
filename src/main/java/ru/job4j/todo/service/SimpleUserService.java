package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Users;
import ru.job4j.todo.store.SimpleUserStore;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleUserService implements UserService {

    private final SimpleUserStore userStore;

    public SimpleUserService(SimpleUserStore userStore) {
        this.userStore = userStore;
    }


    @Override
    public Optional<Users> save(Users user) {
        return userStore.save(user);
    }

    @Override
    public Optional<Users> findByEmailAndPassword(String email, String password) {
        return userStore.findByEmailAndPassword(email, password);
    }

    @Override
    public Collection<Users> findAll() {
        return userStore.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        return userStore.deleteById(id);
    }

    @Override
    public Optional<Users> findById(int id) {
        return userStore.findById(id);
    }
}
