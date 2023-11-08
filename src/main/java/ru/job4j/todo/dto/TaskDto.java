package ru.job4j.todo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private LocalDateTime created = LocalDateTime.now();

    private boolean done;

    private String userName;

    private String priority;

    public TaskDto() {
    }

    public TaskDto(int id, String title, String description, LocalDateTime created,
                   boolean done, String userName, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.done = done;
        this.userName = userName;
        this.priority = priority;
    }

}
