package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.services.ReaderService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
public class ReaderController {
	private final ReaderService readerService;
	private final IssueRepository issueRepository;
	private final String MSGGETALL = "Отправлен запрос на получение списка всех читателей";
	private final String MSGGETBYID = "Отправлен запрос на получение читателя с id={}";
	private final String MSGNOTFOUNDBYID = "Читатель с id=%d не найден";
	private final String MSGDELEBYID = "Отправлен запрос на удаление книги с id={}";
	private final String MSGCREATE = "Отправлен запрос на добавление нового читателя {}";
	private final String MSGGETREADERISSUES = "Отправлен запрос на получение всех запросов пользователя с id={}";

	@GetMapping
	public ResponseEntity<List<Reader>> getAllReaders() {
		log.info(MSGGETALL);
		return ResponseEntity.status(HttpStatus.OK).body(readerService.getAllReaders());
	}

	@GetMapping("{id}")
	public ResponseEntity<String> getReaderById(@PathVariable long id) {
		log.info(MSGGETBYID, id);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(readerService.getReaderById(id).toString());
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(MSGNOTFOUNDBYID, id));
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteReaderById(@PathVariable long id) {
		log.info(MSGDELEBYID, id);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(readerService.deleteReaderById(id).toString());
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(MSGNOTFOUNDBYID, id));
		}
	}

	@PostMapping
	public ResponseEntity<String> createNewReader(@RequestBody String nameReader) {
		log.info(MSGCREATE, nameReader);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(readerService.createReader(nameReader).toString());
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@GetMapping("/{id}/issue")
	public ResponseEntity<List<Issue>> getReaderIssues(@PathVariable long id) {
		log.info(MSGGETREADERISSUES, id);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(issueRepository.getReaderIssuers(id));
		} catch (NullPointerException e) {
		}
		return null;
	}
}
