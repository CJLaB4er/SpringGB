package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.ReaderService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
public class ReaderController {
	private final ReaderService readerService;

	@GetMapping
	public List<Reader> getAllReaders() {
		log.info(LocalDateTime.now() + " Отправлен запрос на получение списка всех читателей");
		return readerService.getAllReaders();
	}

	@GetMapping("{id}")
	public String getReaderById(@PathVariable long id) {
		log.info(LocalDateTime.now() + " Отправлен запрос на получение читателя с id={}", id);
		Reader reader = readerService.getReaderById(id);
		if (reader == null) return String.format("Читатель с id=%d не найден", id);
		return reader.getName();
	}

	@DeleteMapping("{id}")
	public Reader deleteReaderById(@PathVariable long id) {
		log.info(LocalDateTime.now() + " Отправлен запрос на удаление читателя с id={}", id);
		return readerService.deleteReaderById(id);
	}

	@PostMapping
	public Reader createNewBook(@RequestBody String nameReader) {
		log.info(LocalDateTime.now() + String.format(" Отправлен запрос на добавление нового читателя \"%s\"", nameReader));
		return readerService.createReader(nameReader);
	}
}
