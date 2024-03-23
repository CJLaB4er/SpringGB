package ru.gb.springbootlesson3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLesson3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLesson3Application.class, args);


		/*
		1. контроллеры (controllers, api)
		2. Сервисный слой (services)
		3. Репозиторий (repository, dao)
		4. Сущности (entity, model)

		/book/**
		/reader/**
		/issue/**
		 */
	}

}
