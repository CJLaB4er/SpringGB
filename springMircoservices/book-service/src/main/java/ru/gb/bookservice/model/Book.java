package ru.gb.bookservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class Book {
    private UUID id;
    String title;
}
