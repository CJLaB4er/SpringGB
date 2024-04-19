package ru.gb.springtesting.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springtesting.model.Note;
import ru.gb.springtesting.repository.NoteRepository;

import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebClient
//@ActiveProfiles("test")
class NoteControllerTest {

    @Autowired
    WebTestClient webTestClient;


    @Autowired
    NoteRepository noteRepository;

    @Test
    void getAllNotes() {

        noteRepository.deleteAll();

        List<Note> notes = noteRepository.saveAll(List.of(
                        new Note("first", "first content"),
                        new Note("second", "secod content"),
                        new Note("last", "last content")

                )
        );

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
}