package com.cour.micromania.dto;

import java.util.Date;

public record CustomerRequest(
        String firstName,
        String lastName,
        Date birthDate,
        String email,
        boolean hasFidelity
) {
}
