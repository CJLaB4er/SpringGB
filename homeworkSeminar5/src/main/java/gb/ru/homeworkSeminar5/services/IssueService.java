package gb.ru.homeworkSeminar5.services;

import gb.ru.homeworkSeminar5.entity.Book;
import gb.ru.homeworkSeminar5.entity.Issue;
import gb.ru.homeworkSeminar5.entity.Reader;
import gb.ru.homeworkSeminar5.repository.BookRepository;
import gb.ru.homeworkSeminar5.repository.IssueRepository;
import gb.ru.homeworkSeminar5.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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

    @EventListener(ContextRefreshedEvent.class)
    public void addStartIssuesInRepo() {
        issueRepository.save(new Issue(1, 1));
        issueRepository.save(new Issue(1, 2));
        issueRepository.save(new Issue(2, 1));
        issueRepository.save(new Issue(3, 2));
    }
}
