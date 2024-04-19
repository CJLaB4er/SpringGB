package ru.gb.springtesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springtesting.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
