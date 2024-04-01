package ru.netology.losev.OperationHistoryApiApplication.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private long id;
    private String name;
    private int age;
}
