package ru.gb.springbootlesson3.entity;

import lombok.Data;

@Data
public class Book {
	private static long genId;
	private int count = 1; //общее количество данных книг

	private final long id;
	private final String name;

	public Book(String name) {
		id = ++genId;
		this.name = name;
	}


	public Book increaseCount() {
		this.count++;
		return this;
	}

	public Book decreaseCount(){
		if (count > 0) count--;
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
