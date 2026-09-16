package com.cour.micromania.service;

import com.cour.micromania.dto.EmployeeRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Employee;
import com.cour.micromania.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    public List<Employee> findAllByIds(List<Integer> ids) {
        if (ids == null) {
            return new ArrayList<>();
        }
        return ids.stream()
                .map(this::findById)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    public Employee create(EmployeeRequest request) {
        Employee employee = new Employee(
                request.firstName(),
                request.lastName(),
                request.birthDate(),
                request.email(),
                request.role()
        );
        return employeeRepository.save(employee);
    }

    public Employee update(int id, EmployeeRequest request) {
        Employee employee = findById(id);
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setBirthDate(request.birthDate());
        employee.setEmail(request.email());
        employee.setRole(request.role());
        return employeeRepository.save(employee);
    }

    public void delete(int id) {
        employeeRepository.delete(findById(id));
    }
}
