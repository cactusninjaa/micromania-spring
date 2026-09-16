package com.cour.micromania.service;

import com.cour.micromania.dto.CustomerRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Customer;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public Customer findById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public List<Customer> findAllByIds(List<Integer> ids) {
        if (ids == null) {
            return new ArrayList<>();
        }
        return ids.stream()
                .map(this::findById)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Customer create(CustomerRequest request) {
        Customer customer = new Customer(
                request.birthDate(),
                request.firstName(),
                request.lastName(),
                request.email()
        );
        customer.setHasFidelity(request.hasFidelity());
        customers.add(customer);
        return customer;
    }

    public Customer update(int id, CustomerRequest request) {
        Customer customer = findById(id);
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setBirthDate(request.birthDate());
        customer.setEmail(request.email());
        customer.setHasFidelity(request.hasFidelity());
        return customer;
    }

    public void delete(int id) {
        if (!customers.remove(findById(id))) {
            throw new NotFoundException("Customer not found");
        }
    }
}
