package ru.gb.note.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.note.model.Note;
import ru.gb.note.services.NoteService;
import ru.gb.timer.Timer;

import java.util.List;

@Timer
@RestController
@RequestMapping("note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
//                .map(ResponseEntity::ok)
                .map(body -> ResponseEntity.ok().body(body))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.saveNote(note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note newNote) {
        Note note = getNoteById(id).getBody();
        note.setTitle(newNote.getTitle());
        note.setContent(newNote.getContent());
        return noteService.saveNote(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}
