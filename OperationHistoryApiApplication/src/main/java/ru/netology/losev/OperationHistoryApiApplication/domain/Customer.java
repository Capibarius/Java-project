package ru.netology.losev.OperationHistoryApiApplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private long id;
    private String name;
    private int age;
}
