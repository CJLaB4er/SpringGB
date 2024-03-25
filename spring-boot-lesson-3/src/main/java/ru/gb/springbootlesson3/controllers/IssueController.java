package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.services.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

	@Autowired
	private IssueService service;
	private final String MSGNOTFOUNDBYID = "Запрос с id=%d не найден";
	private final String MSGGETALL = "Отправлен запрос на получение списка всех запросов";


	/*
		GET - получение записей
		POST - создание записей
		PUT - изменение записей
		DELETE - запрос на удаление ресурса
	 */
	@GetMapping
	public ResponseEntity<List<Issue>> getAllIssues() {
		log.info(MSGGETALL);
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllIssues());
	}

	@PostMapping
	public ResponseEntity<String> issueBook(@RequestBody IssueRequest issueRequest) {
		log.info("Поступил запрос на выдачу: readerId={}, bookId={}"
				, issueRequest.getReaderId(), issueRequest.getBookId());

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.createIssue(issueRequest).toString());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (RuntimeException e){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<String> getIssueById(@PathVariable long id) {
		log.info("поступил запрос информации о выдаче с id ={}", id);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getIssueById(id).toString());
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(MSGNOTFOUNDBYID, id));
		}
	}

	@PutMapping("{issueId}")
	public ResponseEntity<String> changeIssue(@PathVariable long issueId) {
		log.info("поступил запрос изменении информации о выдаче с id ={}", issueId);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.cahngeIssueById(issueId).toString());
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(MSGNOTFOUNDBYID, issueId));
		}
	}
}
