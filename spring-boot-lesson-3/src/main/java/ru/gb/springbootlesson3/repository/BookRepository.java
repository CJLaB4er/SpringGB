package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
	private List<Book> list = new ArrayList<>();

	public BookRepository() {
		list.add(new Book("Война и мир"));
		list.add(new Book("Мастер и Маргарита"));
		list.add(new Book("Приключения Буратино"));
	}

	public Book findById(long id) {
		return list.stream().filter(e -> e.getId() == id)
				.findFirst()
				.orElse(null);
	}

	public List<Book> getAllBooks() {
		return List.copyOf(list);
	}

	public Book deleteById(long id) {
		Book book = findById(id);
		list.remove(book);
		return book;
	}

	public Book addNewBook(String name) {
		if (list.contains(findByName(name))) {
			return list.get(list.indexOf(name)).increaseCount();

		}
		Book book = new Book(name);
		list.add(book);
		return book;
	}

	public Book findByName(String name) {
		return list.stream()
				.filter(e -> e.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}
}
