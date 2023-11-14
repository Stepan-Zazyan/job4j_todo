package ru.job4j.todo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tasks")
@Data
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String title;

    private String description;

    private LocalDateTime created = LocalDateTime.now();

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToMany
    @JoinTable(
            name = "categories_and_tasks",
            joinColumns = { @JoinColumn(name = "tasks_id") },
            inverseJoinColumns = { @JoinColumn(name = "categories_id") }
    )
    private List<Category> categoryList = new ArrayList<>();

    public Task() {
    }

    public Task(int id, String title, String description, LocalDateTime created, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public boolean getDone() {
        return done;
    }

}
