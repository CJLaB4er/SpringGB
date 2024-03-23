package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.services.BookService;

import java.time.LocalDateTime;
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
	public List<Book> getAllBooks() {
		log.info(MSGGETALL);
		return bookService.getAllBooks();
	}

	@GetMapping("{id}")
	public String getBookById(@PathVariable long id) {
		log.info(MSGGETBYID, id);
		Book book = bookService.getBookById(id);
		if (book == null) return String.format(MSGNOTFOUNDBYID, id);
		return book.getName();
	}

	@DeleteMapping("{id}")
	public Book deleteBookById(@PathVariable long id) {
		log.info(MSGDELEBYID, id);
		return bookService.deleteBookById(id);
	}

	@PostMapping
	public Book createNewBook(@RequestBody String nameBook) {
		log.info(MSGCREATE, nameBook);
		return bookService.createBook(nameBook);
	}


}
