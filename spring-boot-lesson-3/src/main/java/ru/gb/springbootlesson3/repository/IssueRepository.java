package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.services.IssueService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {

	private List<Issue> list = new ArrayList<>();

	public List<Issue> getAllIssues() {
		return List.copyOf(list);
	}


	public void createIssue(Issue issue) {
		list.add(issue);


	}

	public Issue findById(long id) {
		return list.stream().filter(e -> e.getId() == id)
				.findFirst()
				.orElse(null);
	}


	public List<Issue> getReaderIssuers(long id) {
		return list.stream()
				.filter(e -> e.getIdReader() == id)
				.toList();
	}
}
