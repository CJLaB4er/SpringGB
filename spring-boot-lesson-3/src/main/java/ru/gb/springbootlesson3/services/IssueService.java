package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.IssueRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
	private final BookRepository bookRepository;
	private final IssueRepository issueRepository;
	private final ReaderRepository readerRepository;


	@Value("${application.issue.max-allowed-books:1}")
	private Integer LIMIT; // ограничение по кол-ву книг на руках

	public Issue createIssue(IssueRequest request) {
		Reader reader = readerRepository.findById(request.getReaderId());
		if (reader == null) {
			throw new NoSuchElementException(String.format("Читатель с id=%d не найден", request.getReaderId()));
		}
		if (!checkReader(reader)) {
			log.info("У читателя превышен лимит книг на руках");
			throw new RuntimeException("У читателя превышен лимит книг на руках");
		}
		Book book = bookRepository.findById(request.getBookId());
		if (book == null) {
			throw new NoSuchElementException(String.format("Книга с id=%d не найдена", request.getBookId()));
		}
		if (!chekBook(book)) {
			log.info("Запрошенной книги \"{}\" нет в наличии", book.getName());
			throw new RuntimeException(String.format("Запрошенной книги \"%s\" нет в наличии", book.getName()));
		}
		Issue issue = new Issue(request.getReaderId(), request.getBookId());
		issueRepository.createIssue(issue);

		reader.addBook(book);
		book.decreaseCount();

		return issue;
	}

	public Issue getIssueById(long id) {
		Issue issue = issueRepository.findById(id);
		if (issue == null) {
			log.info("запрос с id={}, не найден", id);
		} else {
			log.info("отправлена информаци по запросу с id={}", id);
		}
		return issue;
	}

	private boolean checkReader(Reader reader) {
		return reader.getBookList().size() >= LIMIT ? false : true;
	}

	private boolean chekBook(Book book) {
		return book.getCount() > 0 ? true : false;
	}

	public List<Issue> getAllIssues() {
		return issueRepository.getAllIssues();
	}

	public Issue cahngeIssueById(long id) {
		Issue issue = issueRepository.findById(id);
		Reader reader = readerRepository.findById(issue.getIdReader());
		Book book = bookRepository.findById(issue.getIdBook());
		reader.removeBook(book);
		book.increaseCount();
		issue.setReturned_at(LocalDateTime.now());

		return issue;
	}
}
