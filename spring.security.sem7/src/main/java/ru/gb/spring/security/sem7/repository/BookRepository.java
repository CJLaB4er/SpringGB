package ru.gb.spring.security.sem7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.spring.security.sem7.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
