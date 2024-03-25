package ru.gb.springbootlesson3.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Reader {
	private static long genId;

	private final long id;
	private final String name;
	private List<Book> bookList;

	public Reader(String name) {
		id = ++genId;
		this.name = name;
		bookList = new ArrayList<>();
	}

	public Reader addBook(Book book) {
		bookList.add(book);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				"id=" + id +
				", name='" + name + '\'' +
				", bookList=" + bookList +
				'}';
	}

	public Reader removeBook(Book book) {
		bookList.remove(book);
		return this;
	}
}
