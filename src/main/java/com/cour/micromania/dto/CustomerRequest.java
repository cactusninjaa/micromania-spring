package com.cour.micromania.dto;

import java.time.LocalDate;

public record CustomerRequest(
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email,
        boolean hasFidelity
) {
}
