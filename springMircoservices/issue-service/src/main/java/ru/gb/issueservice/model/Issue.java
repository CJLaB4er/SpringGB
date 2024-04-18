package ru.gb.issueservice.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Issue {
    private UUID id;
    private UUID idBook;
    private UUID idReader;
}
