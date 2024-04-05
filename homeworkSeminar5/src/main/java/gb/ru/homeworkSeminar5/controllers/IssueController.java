package gb.ru.homeworkSeminar5.controllers;

import gb.ru.homeworkSeminar5.entity.Issue;
import gb.ru.homeworkSeminar5.services.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("new")
    public ResponseEntity<String> createIssue(@RequestParam Long idReader, @RequestParam Long idBook) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(issueService.createIssue(idReader, idBook).toString());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Читатель с id=" + idReader +
                    "или книга с id=" + idBook + "не найдены.");
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Превышен лимит книг на руках.");
        }
    }
}
