package ru.netology.losev.OperationHistoryApiApplication.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.losev.OperationHistoryApiApplication.domain.Customer;
import ru.netology.losev.OperationHistoryApiApplication.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAge()))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CustomerDTO getCustomer(@PathVariable("id") long id) {
        Customer customer = customerService.getCustomer(id);
        if (customer != null)
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getAge());
        else
            return null;
    }

    @PostMapping
    public void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @PutMapping("{id}")
    public void updateCustomer(@PathVariable("id") long id, @RequestBody Customer updatedCustomer) {
        updatedCustomer.setId(id);
        customerService.updateCustomer(updatedCustomer);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
    }

}