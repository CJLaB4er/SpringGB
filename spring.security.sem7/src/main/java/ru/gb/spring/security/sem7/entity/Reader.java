package ru.gb.spring.security.sem7.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "readers")
@NoArgsConstructor
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    // необходимо переделать на коллекцию книг
    private String bookList = "[]";
    @NonNull
    private String login;
    private String password;
    @NonNull
    private String role;

    public Reader(String name, String login, String password, String role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Reader addBook(Book book) {
        if (bookList.length() > 2) {
            bookList = bookList.substring(0, bookList.length() - 1) + ", " + book.getTitle() + ']';
        } else bookList = '[' + book.getTitle() + ']';
        return this;
    }

    public Reader returnBook(Book book) {
        bookList = bookList.replaceFirst(book.getTitle(), "");
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\"" + ":" + id +
                ", \"name\"" + ":\"" + name + "\"" +
                ", \"books\"" + ":" + bookList +
                '}';
    }
}
