package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.springbootlesson3.services.BookService;
import ru.gb.springbootlesson3.services.ReaderService;

@Controller
@RequiredArgsConstructor
public class HTTPReaderController {
	private final ReaderService readerService;

	@GetMapping("ui/readers")
	public String getHTMLBooks(Model model) {
		model.addAttribute("table", readerService.getAllReaders());
		return "readers";
	}

}
