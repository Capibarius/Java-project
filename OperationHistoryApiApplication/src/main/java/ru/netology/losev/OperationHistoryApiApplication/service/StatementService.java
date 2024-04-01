package ru.netology.losev.OperationHistoryApiApplication.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.netology.losev.OperationHistoryApiApplication.domain.Operation;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService {
    private List<Operation> operations;
    private long counter;

    public StatementService() {
        operations = new ArrayList<>();
        counter = 0;
    }

    @PostConstruct
    public void initStorage() {
        operations.add(new Operation(++counter, 4000, "2024-01-01"));
        operations.add(new Operation(++counter, 23000, "2023-12-02"));
        operations.add(new Operation(++counter, 500, "2023-12-03"));
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Operation getOperation(long id) {
        for (Operation operation : operations) {
            if (operation.getId() == id)
                return operation;
        }
        return null;
    }

    public void createOperation(Operation operation) {
        operation.setId(++counter);
        operations.add(operation);
    }

    public void updateOperation(Operation updatedOperation) {
        for (Operation operation : operations) {
            if (operation.getId() == updatedOperation.getId()) {
                operation.setAmount(updatedOperation.getAmount());
                operation.setDate(updatedOperation.getDate());
                break;
            }
        }
    }

    public void deleteOperation(long id) {
        operations.removeIf(operation -> operation.getId() == id);
    }
}