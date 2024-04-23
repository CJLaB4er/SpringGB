package ru.gb.note.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.note.model.Note;
import ru.gb.note.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return List.copyOf(noteRepository.findAll());
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
