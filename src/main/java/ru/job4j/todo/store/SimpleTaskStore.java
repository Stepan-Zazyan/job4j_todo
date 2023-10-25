package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleTaskStore implements TaskStore {

    private final SessionFactory sf;

    @Override
    public Task add(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return task;
    }

    @Override
    public boolean update(Task task) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Task SET title = :fTitle , description = :fDescription, created = :fCreated, done = :fDone "
                                    + "WHERE id = :fId", Task.class)
                    .setParameter("fTitle", task.getTitle())
                    .setParameter("fDescription", task.getDescription())
                    .setParameter("fCreated", task.getCreated())
                    .setParameter("fDone", task.getDone())
                    .setParameter("fId", task.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return rsl;
    }

    @Override
    public boolean updateDone(Task task) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Task SET done = :fDone WHERE id = :fId", Task.class)
                    .setParameter("fDone", true)
                    .setParameter("fId", task.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return rsl;
    }

    @Override
    public boolean delete(Integer id) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.createQuery("delete Task where id = :fId", Task.class)
                    .setParameter("fId", id)
                    .executeUpdate();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return rsl;
    }

    @Override
    public List<Task> findAll() {
        Session session = sf.openSession();
        List<Task> result = new ArrayList<>();
        try {
            Query<Task> query = session.createQuery("from Task", Task.class);
            result = new ArrayList<>(query.list());
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public Optional<Task> findById(Integer id) {
        Session session = sf.openSession();
        Task task = new Task();
        try {
            Query<Task> query = session
                    .createQuery("from Task where id = :fId", Task.class);
            query.setParameter("fId", id);
            task = query.uniqueResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return Optional.ofNullable(task);
    }

    @Override
    public void close() throws SQLException {
        sf.close();
    }

}