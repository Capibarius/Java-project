package ru.netology.losev.OperationHistoryApiApplication.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.netology.losev.OperationHistoryApiApplication.domain.Customer;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService {
    private List<Customer> customers;
    private long counter;

    public CustomerService() {
        customers = new ArrayList<>();
        counter = 0;
    }

    @PostConstruct
    public void initStorage() {
        customers.add(new Customer(++counter, "Ilia", 27));
        customers.add(new Customer(++counter, "Dimitri", 30));
        customers.add(new Customer(++counter, "Vladimir", 17));
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(long id) {
        for (Customer customer : customers) {
            if (customer.getId() == id)
                return customer;
        }
        return null;
    }

    public void createCustomer(Customer customer) {
        customer.setId(++counter);
        customers.add(customer);
    }

    public void updateCustomer(Customer updatedCustomer) {
        for (Customer customer : customers) {
            if (customer.getId() == updatedCustomer.getId()) {
                customer.setName(updatedCustomer.getName());
                customer.setAge(updatedCustomer.getAge());
                break;
            }
        }
    }

    public void deleteCustomer(long id) {
        customers.removeIf(customer -> customer.getId() == id);
    }
}

