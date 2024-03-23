package ru.gb.springbootlesson3.entity;

import lombok.Data;

import java.util.List;

@Data
public class Reader {
    private static long genId;

    private final long id;
    private final String name;
    private List<Book> bookList;

    public Reader(String name) {
        id = ++genId;
        this.name = name;
    }
}
