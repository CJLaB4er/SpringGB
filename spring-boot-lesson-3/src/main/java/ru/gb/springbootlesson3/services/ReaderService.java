package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReaderService {
	private final ReaderRepository readerRepository;
	private final IssueRepository issueRepository;

	public List<Reader> getAllReaders() {
		log.info("Выведен список всех читателей");
		return readerRepository.getAllReaders();
	}

	public Reader getReaderById(long id) {
		Reader reader = readerRepository.findById(id);
		if (reader == null) {
			log.info("Читатель с id={}, не найдена", id);
		} else {
			log.info("Отправлено имя читателя с id={}", id);
		}
		return reader;
	}

	public Reader deleteReaderById(long id) {
		Reader reader = readerRepository.deleteById(id);
		if (reader == null) {
			log.info("Читатель с id={}, не найден, запрос на удаление не выполнен", id);
		} else {
			log.info("Читатлель с id={} удален", id);
		}
		return reader;
	}

	public Reader createReader(String name) {
		Reader reader = readerRepository.addNewReader(name);
		log.info(String.format("В репозиторий добавлен новый читатель \"%s\", присвоен id=\"%d\""
				, name, reader.getId()));
		return reader;
	}




}
