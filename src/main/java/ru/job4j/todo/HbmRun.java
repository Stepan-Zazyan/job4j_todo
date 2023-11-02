/*
package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.Users;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            var users = new Users();
            users.setName("ADMIN");
            create(users, sf);
            var task = new Task();
            task.setTitle("Admin Admin");
            task.setUsers(users);
            create(task, sf);
            findAll(Task.class, sf)
                    .forEach(System.out::println);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> void create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.persist(model);
        session.getTransaction().commit();
        session.close();
    }

    public static <T> List<T> findAll(Class<T> cl, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + cl.getName(), cl).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}*/
