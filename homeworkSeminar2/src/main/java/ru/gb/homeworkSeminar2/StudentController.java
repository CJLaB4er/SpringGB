package ru.gb.homeworkSeminar2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//3. Создать контроллер, обрабатывающий входящие запросы:
@RestController
public class StudentController {
    private StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    //    3.1 GET /student/{id} - получить студента по ID
    //    http://localhost:8080/student/2
    @GetMapping("student/{id}")
    public Student getById(@PathVariable long id) {
        return repository.getById(id);
    }

    //    3.2 GET /student - получить всех студентов
    //    http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    //    3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
    //    http://localhost:8080/student?name=studentName
    @GetMapping("student")
    public List<Student> getAllStudents(@RequestParam String name) {
        return repository.getByName(name);
    }

    //    3.4 GET /group/{groupName}/student - получить всех студентов группы
    //    http://localhost:8080/group/secondGroup/student
    @GetMapping("group/{groupName}/student")
    public List<Student> getByGroupName(@PathVariable String groupName) {
        return repository.getByNameGroup(groupName);
    }

}
