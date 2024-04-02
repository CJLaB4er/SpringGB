package ru.gb.springbootlesson3.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssueRequest {
    private long readerId;
    private long bookId;
}
