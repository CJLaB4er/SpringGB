package gb.ru.homeworkSeminar5.repository;

import gb.ru.homeworkSeminar5.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
