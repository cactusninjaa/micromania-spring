package com.cour.micromania.dto;

import java.util.List;

public record ShopRequest(
        int zipCode,
        String city,
        String streetName,
        int streetNumber,
        List<Integer> employeeIds,
        List<Integer> customerIds,
        Float monitaryBase,
        boolean open
) {
}
