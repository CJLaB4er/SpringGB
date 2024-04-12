package ru.gb.spring.security.sem7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.spring.security.sem7.entity.Book;
import ru.gb.spring.security.sem7.entity.Issue;
import ru.gb.spring.security.sem7.entity.Reader;
import ru.gb.spring.security.sem7.repository.BookRepository;
import ru.gb.spring.security.sem7.repository.IssueRepository;
import ru.gb.spring.security.sem7.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue findIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow();
    }

    public Issue createIssue(Long idReader, Long idBook) {
        Reader reader = readerRepository.findById(idReader).orElseThrow();
        if (checkReader(reader)) return null;
        Book book = bookRepository.findById(idBook).orElseThrow();
        reader.addBook(book);
        return issueRepository.save(new Issue(idReader, idBook));
    }

    public boolean checkReader(Reader reader) {
        String[] books = reader.getBookList().split(",");
        return books.length > 1 ? true : false;
    }

    public Issue returnBook(Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        issue.setReturned_at(LocalDateTime.now());
        issueRepository.save(issue);
        Reader reader = readerRepository.findById(issue.getIdReader()).orElseThrow();
        reader.returnBook(bookRepository.findById(issue.getIdBook()).orElseThrow());
        readerRepository.save(reader);
        return issue;
    }

// со слушателем не получается, сделана ручка Post issue/new
//    @EventListener(ContextRefreshedEvent.class)
    public void addStartIssues() {
        createIssue(1l, 1l);
        createIssue(1l, 2l);
        createIssue(2l, 1l);
        createIssue(3l, 4l);
        returnBook(1L);
        returnBook(3L);
    }
}
