package com.cour.micromania.service;

import com.cour.micromania.dto.CustomerRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Customer;
import com.cour.micromania.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer findById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public List<Customer> findAllByIds(List<Integer> ids) {
        if (ids == null) {
            return new ArrayList<>();
        }
        return ids.stream()
                .map(this::findById)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer create(CustomerRequest request) {
        Customer customer = new Customer(
                request.birthDate(),
                request.firstName(),
                request.lastName(),
                request.email()
        );
        customer.setHasFidelity(request.hasFidelity());
        return customerRepository.save(customer);
    }

    public Customer update(int id, CustomerRequest request) {
        Customer customer = findById(id);
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setBirthDate(request.birthDate());
        customer.setEmail(request.email());
        customer.setHasFidelity(request.hasFidelity());
        return customerRepository.save(customer);
    }

    public void delete(int id) {
        customerRepository.delete(findById(id));
    }
}
