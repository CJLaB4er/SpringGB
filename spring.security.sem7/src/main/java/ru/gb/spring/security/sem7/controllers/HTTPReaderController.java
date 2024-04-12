package ru.gb.spring.security.sem7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.spring.security.sem7.services.ReaderService;

@Controller
@RequiredArgsConstructor
public class HTTPReaderController {
    private final ReaderService readerService;

    @GetMapping("ui/readers")
    public String getHTMLBooks(Model model) {
        model.addAttribute("table", readerService.getAllReaders());
        return "readers";
    }

    @GetMapping("/ui/reader/{id}")
    public String getHTMLReader(@PathVariable long id, Model model) {
        model.addAttribute("table", readerService.findReaderById(id));
        return "reader";
    }

}
