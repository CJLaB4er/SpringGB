package gb.ru.homeworkSeminar5.controllers;

import gb.ru.homeworkSeminar5.entity.Reader;
import gb.ru.homeworkSeminar5.services.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping
    public List<Reader> getAllReaders(){
        return readerService.getAllReaders();
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getReaderById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(readerService.findReaderById(id).toString());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Читатель с id=" + id + " не найден.");
        }

    }

    @DeleteMapping("{id}")
    public void deleteReaderById(@PathVariable Long id) {
        readerService.deleteReaderById(id);
    }
}
