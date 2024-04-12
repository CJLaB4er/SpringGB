package ru.gb.spring.security.sem7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.spring.security.sem7.entity.Reader;
import ru.gb.spring.security.sem7.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader findReaderById(Long id) {
        return readerRepository.findById(id).orElseThrow();
    }

    public void deleteReaderById(Long id) {
        readerRepository.deleteById(id);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addStartReadersInRepo() {
        readerRepository.save(new Reader("Константин", "konst", "konst", "admin"));
        readerRepository.save(new Reader("Николай", "nick", "nick", "admin"));
        readerRepository.save(new Reader("Ольга", "olga", "olga", "user"));
        readerRepository.save(new Reader("Виктор", "victor", "victor", "user"));
    }
}
