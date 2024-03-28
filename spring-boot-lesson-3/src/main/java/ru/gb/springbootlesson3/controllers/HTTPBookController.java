package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.springbootlesson3.services.BookService;

@Controller
@RequiredArgsConstructor
public class HTTPBookController {
	private final BookService bookService;

	@GetMapping("ui/books")
	public String getHTMLBooks(Model model) {
		model.addAttribute("table", bookService.getAllBooks());
		return "books";
	}

}
