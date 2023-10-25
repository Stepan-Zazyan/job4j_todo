package ru.job4j.todo.store;

import ru.job4j.todo.model.Users;

import java.util.Collection;
import java.util.Optional;

public interface UserStore {
    Optional<Users> save(Users user);

    Optional<Users> findByEmailAndPassword(String email, String password);

    Collection<Users> findAll();

    boolean deleteById(int id);

    Optional<Users> findById(int id);
}
