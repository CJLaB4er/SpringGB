package ru.gb.spring.security.sem7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.spring.security.sem7.repository.BookRepository;
import ru.gb.spring.security.sem7.repository.ReaderRepository;
import ru.gb.spring.security.sem7.services.IssueService;

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
