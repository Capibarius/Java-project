package ru.netology.losev.OperationHistoryApiApplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation {
    private long id;
    private double amount;
    private String date;
}
