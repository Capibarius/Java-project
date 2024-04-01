package ru.netology.losev.OperationHistoryApiApplication.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.losev.OperationHistoryApiApplication.domain.Operation;
import ru.netology.losev.OperationHistoryApiApplication.service.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/operations")
public class OperationController {

    private final StatementService statementService;

    public OperationController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping
    public List<OperationDTO> getOperations() {
        List<Operation> operations = statementService.getOperations();
        return operations.stream()
                .map(operation -> new OperationDTO(operation.getId(), operation.getAmount(), operation.getDate()))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public OperationDTO getOperation(@PathVariable("id") long id) {
        Operation operation = statementService.getOperation(id);
        if (operation != null)
            return new OperationDTO(operation.getId(), operation.getAmount(), operation.getDate());
        else
            return null;
    }

    @PostMapping
    public void createOperation(@RequestBody Operation operation) {
        statementService.createOperation(operation);
    }

    @PutMapping("{id}")
    public void updateOperation(@PathVariable("id") long id, @RequestBody Operation updatedOperation) {
        updatedOperation.setId(id);
        statementService.updateOperation(updatedOperation);
    }

    @DeleteMapping("{id}")
    public void deleteOperation(@PathVariable("id") long id) {
        statementService.deleteOperation(id);
    }
}