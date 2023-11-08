package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;

import java.sql.SQLException;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimplePriorityStore implements PriorityStore {

    private final SessionFactory sf;

    @Override
    public Optional<Priority> findById(Integer id) {
        Session session = sf.openSession();
        Priority priority = new Priority();
        try {
            Query<Priority> query = session
                    .createQuery("from Priority where id = :fId", Priority.class);
            query.setParameter("fId", id);
            priority = query.uniqueResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(priority);
    }

    @Override
    public void close() throws SQLException {
        sf.close();
    }

}
