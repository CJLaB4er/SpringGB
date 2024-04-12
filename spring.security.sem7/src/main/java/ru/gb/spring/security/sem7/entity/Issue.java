package ru.gb.spring.security.sem7.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "issues")
@RequiredArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idReader;
    private long idBook;
    private final LocalDateTime issued_at;
    private LocalDateTime returned_at;

    public Issue() {
        issued_at = LocalDateTime.now();
    }


    public Issue(long idReader, long idBook) {
        this.idReader = idReader;
        this.idBook = idBook;
        issued_at = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"idReader\":" + idReader +
                ", \"idBook\":" + idBook +
                ", \"issued_at\":" + issued_at +
                ", \"returned_at\":" + returned_at +
                '}';
    }
}
