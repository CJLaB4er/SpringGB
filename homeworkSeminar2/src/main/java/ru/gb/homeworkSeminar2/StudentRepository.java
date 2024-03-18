package ru.gb.homeworkSeminar2;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("studentRepo")
public class StudentRepository {
    private final List<Student> students;

    public StudentRepository(){
        students = new ArrayList<>();
        students.add(new Student("Иван", "firstGroup"));
        students.add(new Student("Олег", "firstGroup"));
        students.add(new Student("Кирилл", "secondGroup"));
        students.add(new Student("Виктор", "secondGroup"));
        students.add(new Student("Алексадр", "secondGroup"));
        students.add(new Student("Вадим", "secondGroup"));
        students.add(new Student("ВадstudentNameим", "firstGroup"));
        students.add(new Student("studentName", "firstGroup"));
    }

    public Student getById(long id){
        return students.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAllStudents(){
        return List.copyOf(students);
    }

    public List<Student> getByName(String name){
        return students.stream()
                .filter(x -> x.getName().contains(name))
                .toList();
    }

    public List<Student> getByNameGroup(String groupName){
        return students.stream()
                .filter(x -> x.getGroupName().contains(groupName))
                .toList();
    }
}
