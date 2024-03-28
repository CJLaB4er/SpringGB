package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.services.BookService;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {
	private final BookService bookService;
	private final String MSGGETALL = "Отправлен запрос на получение списка всех книг";
	private final String MSGGETBYID = "Отправлен запрос на получение название книги с id={}";
	private final String MSGNOTFOUNDBYID = "Книга с id=%d не найдена";
	private final String MSGDELEBYID = "Отправлен запрос на удаление книги с id={}";
	private final String MSGCREATE = "Отправлен запрос на добавление новой книги {}";

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		log.info(MSGGETALL);
		return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
	}

	@GetMapping("{id}")
	public ResponseEntity<String> getBookById(@PathVariable long id) {
		log.info(MSGGETBYID, id);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id).toString());
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(MSGNOTFOUNDBYID, id));
		}
	}


	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable long id) {
		log.info(MSGDELEBYID, id);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBookById(id) + " запись удалена.");
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(MSGNOTFOUNDBYID, id));
		}
	}

	@PostMapping
	public Book createNewBook(@RequestBody String nameBook) {
		log.info(MSGCREATE, nameBook);
		return bookService.createBook(nameBook);
	}


}


