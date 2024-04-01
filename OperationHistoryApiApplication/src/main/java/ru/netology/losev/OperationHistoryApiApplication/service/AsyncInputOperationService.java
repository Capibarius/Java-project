package ru.netology.losev.OperationHistoryApiApplication.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.losev.OperationHistoryApiApplication.domain.Operation;
import org.springframework.stereotype.Service;
import ru.netology.losev.OperationHistoryApiApplication.config.OperationProperties;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class AsyncInputOperationService {

    private final Queue<Operation> operations = new LinkedList<>();

    @Autowired
    private StatementService statementService;

    @Autowired
    private OperationProperties operationProperties;

    @PostConstruct
    public void init() {
        this.startProcessing();
    }

    public void startProcessing() {
        Thread t = new Thread(this::processQueue);
        t.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = operations.poll();
            if (operation == null) {
                try {
                    System.out.println("No operations");
                    Thread.sleep(operationProperties.getSleepMilliSeconds());
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Processing operation " + operation);
                processOperation(operation);
            }
        }
    }

    private void processOperation(Operation operation) {
        statementService.createOperation(operation);
    }

    public boolean addOperation(Operation operation) {
        System.out.println("Operation added for processing " + operation);
        return operations.offer(operation);
    }

    public List<Operation> getOperations(long customerId) {
        return null;
    }

    public void deleteOperation(long customerId, long operationId) {
    }
}