package gb.ru.homeworkSeminar5.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String bookList = "[]";

    public Reader(String name) {
        this.name = name;
    }

    public Reader addBook(Book book) {
        if (bookList.length() > 2) {
            bookList = bookList.substring(0, bookList.length() - 1) + ", " + book.getTitle() + ']';
        } else bookList = '[' + book.getTitle() + ']';
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
