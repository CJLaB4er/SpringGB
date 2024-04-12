package ru.gb.spring.security.sem7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.security.sem7.entity.Book;
import ru.gb.spring.security.sem7.services.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getBookById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findBookById(id).toString());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Книга с id=" + id + " не найдена.");
        }
    }

    @DeleteMapping("{id}")
    public void deleteBookById(@PathVariable Long id) {
        service.deleteBookById(id);
    }
}
