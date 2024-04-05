package gb.ru.homeworkSeminar5.services;

import gb.ru.homeworkSeminar5.entity.Reader;
import gb.ru.homeworkSeminar5.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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
        readerRepository.save(new Reader("Константин"));
        readerRepository.save(new Reader("Николай"));
        readerRepository.save(new Reader("Ольга"));
        readerRepository.save(new Reader("Виктор"));
    }
}
