package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;

	public List<Book> getAllBooks() {
		log.info(LocalDateTime.now() + " Выведен список всех книг");
		return bookRepository.getAllBooks();
	}

	public Book getBookById(long id) {
		Book book = bookRepository.findById(id);
		if (book == null) {
			log.info(LocalDateTime.now() + " Книга с id={}, не найдена", id);
		} else {
			log.info(LocalDateTime.now() + " Отправлено наименовании книги с id={}", id);
		}
		return book;
	}

	public Book deleteBookById(long id) {
		Book book = bookRepository.deleteById(id);
		if (book == null) {
			log.info(LocalDateTime.now() + " Книга с id={}, не найдена, запрос на удаление не выполнен", id);
		} else {
			log.info(LocalDateTime.now() + " Книга с id={} удалена", id);
		}
		return book;
	}

	public Book createBook(String name) {
		Book book = bookRepository.addNewBook(name);
		log.info(LocalDateTime.now() + String.format(" в репозиторий добавлена новая книга \"%s\", присвоен id=\"%d\""
				, name, book.getId()));
		return book;
	}


}
