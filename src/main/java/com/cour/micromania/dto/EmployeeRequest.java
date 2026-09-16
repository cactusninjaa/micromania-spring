package com.cour.micromania.dto;

import com.cour.micromania.model.Role;

import java.time.LocalDate;

public record EmployeeRequest(
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email,
        Role role
) {
}
