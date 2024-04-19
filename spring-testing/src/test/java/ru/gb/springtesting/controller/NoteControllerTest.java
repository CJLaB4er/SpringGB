package ru.gb.springtesting.controller;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import ru.gb.springtesting.model.Note;
import ru.gb.springtesting.repository.NoteRepository;

import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebClient
@ActiveProfiles("test")
class NoteControllerTest {

    @Autowired
    WebTestClient webTestClient;


    @Autowired
    NoteRepository noteRepository;


    @Test
    void getAllNotes() {
        noteRepository.saveAll(List.of(
                        new Note("first", "first content"),
                        new Note("second", "secod content"),
                        new Note("last", "last content")

                )
        );

        List<Note> notes = noteRepository.findAll();

        List<Note> notesTest = webTestClient.get()
                .uri("note")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Note>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(notes.size(), notesTest.size());

        for (Note note : notes) {
            boolean flag = notesTest.stream()
                    .filter(item -> Objects.equals(note.getId(), item.getId()))
                    .anyMatch(it -> Objects.equals(note.getTitle(), it.getTitle()));

            Assertions.assertTrue(flag);
        }

    }

    @Test
    void getNoteById() {
        Note note = noteRepository.save(new Note("testById", "testingById"));

        Note noteTest = webTestClient.get()
                .uri("note/" + note.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Note.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(noteTest);
        Assertions.assertEquals(note.getId(), noteTest.getId());
        Assertions.assertEquals(note.getTitle(), noteTest.getTitle());
        Assertions.assertEquals(note.getContent(), noteTest.getContent());
    }

    @Test
    void createNote() {
        Note newNote = new Note("newNote", "newContent");

        Note createdNote = webTestClient.post()
                .uri("note")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(newNote))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Note.class)
                .returnResult()
                .getResponseBody();


        Assertions.assertNotNull(createdNote);
        Assertions.assertEquals(newNote.getTitle(), createdNote.getTitle());
        Assertions.assertEquals(newNote.getContent(), createdNote.getContent());
    }

    @Test
    void deleteNote() {
        noteRepository.deleteAll();

        Note note = noteRepository.save(new Note("deleteMe", "noteContent"));

        webTestClient.delete()
                .uri("note/" + note.getId())
                .exchange()
                .expectStatus().isOk();

        List<Note> notes = webTestClient.get()
                .uri("note")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Note>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertTrue(notes.isEmpty());
    }

    @Test
    void updateNote() {
        noteRepository.deleteAll();

        Note note = noteRepository.save(new Note("newNote", "newNote"));

        Note updateNote = new Note("update", "update");

        Note testNote = webTestClient.put()
                .uri("note/" + note.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(updateNote))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Note.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(testNote);
        Assertions.assertEquals(note.getId(), testNote.getId());
        Assertions.assertEquals(updateNote.getTitle(), testNote.getTitle());
        Assertions.assertEquals(updateNote.getContent(), testNote.getContent());

    }
}


