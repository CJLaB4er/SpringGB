package ru.gb.spring.security.sem7.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;


    public Book(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\"" + ":" + id +
                ", \"title\"" + ":\"" + title + "\"" +
                '}';
    }
}
