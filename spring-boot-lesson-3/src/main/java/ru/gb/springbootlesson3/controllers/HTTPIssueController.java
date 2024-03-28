package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;
import ru.gb.springbootlesson3.services.BookService;
import ru.gb.springbootlesson3.services.IssueService;

@Controller
@RequiredArgsConstructor
public class HTTPIssueController {
	private final IssueService issueService;
	private final BookRepository bookRepository;
	private final ReaderRepository readerRepository;

	@GetMapping("ui/issues")
	public String getHTMLBooks(Model model) {
		model.addAttribute("table", issueService.getAllIssues());
		model.addAttribute("bookRepo", bookRepository);
		model.addAttribute("readerRepo", readerRepository);
		return "issues";
	}

}
