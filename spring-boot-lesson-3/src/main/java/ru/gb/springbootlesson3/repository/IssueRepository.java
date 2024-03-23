package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {
	private List<Issue> list = new ArrayList<>();

	public IssueRepository() {
		list.add(new Issue(1, 2));
		list.add(new Issue(2, 2));
		list.add(new Issue(3, 1));
		list.add(new Issue(1, 1));
	}

	public void createIssue(Issue issue) {
		list.add(issue);

	}

	public Issue findById(long id) {
		return list.stream().filter(e -> e.getId() == id)
				.findFirst()
				.orElse(null);
	}


}
