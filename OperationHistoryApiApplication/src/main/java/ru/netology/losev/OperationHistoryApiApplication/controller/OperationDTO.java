package ru.netology.losev.OperationHistoryApiApplication.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationDTO {
    private long id;
    private double amount;
    private String date;
}
