package com.cour.micromania.dto;

import com.cour.micromania.model.Color;
import com.cour.micromania.model.State;

import java.time.LocalDate;
import java.util.List;

public record ConsoleRequest(
        String name,
        String brand,
        Float price,
        State state,
        LocalDate releaseDate,
        int quantityInStock,
        List<Color> colors
) {
}
