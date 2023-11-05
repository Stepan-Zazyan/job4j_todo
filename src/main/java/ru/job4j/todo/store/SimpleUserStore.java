package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.Users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleUserStore implements UserStore {

    private final SessionFactory sf;

    @Override
    public Optional<Users> save(Users user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Users> findByEmailAndPassword(String email, String password) {
        Session session = sf.openSession();
        Users user = new Users();
        try {
            session.beginTransaction();
            Query<Users> query = session.createQuery("from Users where login = :fEmail and password = :fPassword", Users.class)
                    .setParameter("fEmail", email)
                    .setParameter("fPassword", password);
            user = query.uniqueResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Collection<Users> findAll() {
        Session session = sf.openSession();
        List<Users> result = new ArrayList<>();
        try {
            Query<Users> query = session.createQuery("from Users", Users.class);
            result = new ArrayList<>(query.list());
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.createQuery("delete Users where id = :fId", Users.class)
                    .setParameter("fId", id)
                    .executeUpdate();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Optional<Users> findById(int id) {
        Session session = sf.openSession();
        Users user = new Users();
        try {
            Query<Users> query = session
                    .createQuery("from Users where id = :fId", Users.class);
            query.setParameter("fId", id);
            user = query.uniqueResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(user);
    }
}
