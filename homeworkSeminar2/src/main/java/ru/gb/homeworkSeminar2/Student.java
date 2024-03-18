package ru.gb.homeworkSeminar2;

import lombok.Data;


@Data
public class Student {
    private static long currentId = 0;
    private final long id;
    private String groupName;
    private final String name;

    public Student(String name, String groupName) {
        this.id = currentId++;
        this.name = name;
        this.groupName = groupName;
    }
}
