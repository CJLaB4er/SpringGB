package ru.gb.spring.security.sem7.repository;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.spring.security.sem7.entity.Issue;
import ru.gb.spring.security.sem7.services.IssueService;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}
