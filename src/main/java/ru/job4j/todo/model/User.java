package ru.job4j.todo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String login;

    private String password;

    private String timezone;


    public User() {
    }

    public User(int id, String name, String login, String password, String timezone) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.timezone = timezone;
    }

}
