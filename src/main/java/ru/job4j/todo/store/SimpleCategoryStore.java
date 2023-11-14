package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
@AllArgsConstructor
public class SimpleCategoryStore implements CategoryStore {

    private final SessionFactory sf;

    @Override
    public List<Category> findAll() {
        Session session = sf.openSession();
        List<Category> result = new ArrayList<>();
        try {
            Query<Category> query = session.createQuery("from Category", Category.class);
            result = new ArrayList<>(query.list());
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }


    @Override
    public void close() throws SQLException {
        sf.close();
    }
}
