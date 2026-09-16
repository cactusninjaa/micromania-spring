package com.cour.micromania.service;

import com.cour.micromania.dto.EmployeeRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Employee;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public Employee findById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    public List<Employee> findAllByIds(List<Integer> ids) {
        if (ids == null) {
            return new ArrayList<>();
        }
        return ids.stream()
                .map(this::findById)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Employee create(EmployeeRequest request) {
        Employee employee = new Employee(
                request.firstName(),
                request.lastName(),
                request.birthDate(),
                request.email(),
                request.role()
        );
        employees.add(employee);
        return employee;
    }

    public Employee update(int id, EmployeeRequest request) {
        Employee employee = findById(id);
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setBirthDate(request.birthDate());
        employee.setEmail(request.email());
        employee.setRole(request.role());
        return employee;
    }

    public void delete(int id) {
        if (!employees.remove(findById(id))) {
            throw new NotFoundException("Employee not found");
        }
    }
}
