package com.cour.micromania.dto;

import com.cour.micromania.model.Role;

import java.util.Date;

public record EmployeeRequest(
        String firstName,
        String lastName,
        Date birthDate,
        String email,
        Role role
) {
}
