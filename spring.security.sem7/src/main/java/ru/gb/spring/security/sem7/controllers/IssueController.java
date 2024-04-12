package ru.gb.spring.security.sem7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.security.sem7.entity.Issue;
import ru.gb.spring.security.sem7.services.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.status(HttpStatus.OK).body(issueService.getAllIssues());
    }

    @GetMapping("{id}")
    public ResponseEntity<String> findIssueById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(issueService.findIssueById(id).toString());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Запрос с id=" + id + " не найден.");
        }
    }

    @PostMapping()
    public ResponseEntity<String> createIssue(@RequestParam Long idReader, @RequestParam Long idBook) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(issueService.createIssue(idReader, idBook).toString());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Читатель с id=" + idReader +
                    "или книга с id=" + idBook + "не найдены.");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Превышен лимит книг на руках.");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> returnBook(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(issueService.returnBook(id).toString());
        } catch (NoSuchElementException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не существует факта выдачи книги с id=" + id);
        }
        return null;
    }


    // метод для генерации стартовых значенией в репозитории
    @PostMapping("new")
    public void addStatrIssues(){
        issueService.addStartIssues();
    }

}
