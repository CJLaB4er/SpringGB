package gb.ru.homeworkSeminar5.services;

import gb.ru.homeworkSeminar5.entity.Book;
import gb.ru.homeworkSeminar5.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addStartBooksInRepo(){
        bookRepository.save(new Book("Война и мир"));
        bookRepository.save(new Book("Мастер и Маргарита"));
        bookRepository.save(new Book("Преступление и наказание"));
        bookRepository.save(new Book("Яма"));
        bookRepository.save(new Book("Мёртвые души"));

    }
}
