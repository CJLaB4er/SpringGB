package ru.gb.issueservice.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.issueservice.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class IssueControler {
    private List<Issue> list = new ArrayList<>();
    private final BookProvider provider;

    @PostConstruct
    public void generateIssue() {
        list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());
            issue.setIdReader(UUID.randomUUID());
            issue.setIdBook(provider.getRandomBookId());

            list.add(issue);
        }
    }

    @GetMapping("refresh")
    public List<Issue> refresh() {
        generateIssue();
        return list;
    }

    @GetMapping("issue")
    public List<Issue> getAll() {
        return list;
    }
}
