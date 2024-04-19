package ru.gb.springtesting.services;

import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gb.springtesting.model.Note;
import ru.gb.springtesting.repository.NoteRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;


    @Autowired
    private NoteRepository noteRepository;

    @Before
    public void setUp() {
        noteRepository.deleteAll();
    }

    @Test
    public void getIntegrationTest() {
        Note note = new Note();
        note.setTitle("test_note");
        note.setContent("test_content");

        noteRepository.save(note);

        List<Note> notes = noteService.getAllNotes();

        Assert.assertTrue(notes.size() > 0);

        Assert.assertEquals(note.getTitle(), notes.get(0).getTitle());

    }

}
