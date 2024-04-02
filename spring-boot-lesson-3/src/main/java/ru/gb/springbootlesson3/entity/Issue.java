package ru.gb.springbootlesson3.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Issue {
    private static long genId;

    private final long id;
    private final long idReader;
    private final long idBook;
    private final LocalDateTime issued_at;
    private LocalDateTime returned_at;

    public Issue(long idReader, long idBook) {
        id = ++genId;
        this.idBook = idBook;
        this.idReader = idReader;
        issued_at = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yy");
        String text = dtf.format(LocalDateTime.now());
        return "{" +
                "id=" + id +
                ", idReader=" + idReader +
                ", idBook=" + idBook +
                ", get=" + dtf.format(issued_at) +
                ", return=" + dtf.format(returned_at) +
                '}';
    }
}

