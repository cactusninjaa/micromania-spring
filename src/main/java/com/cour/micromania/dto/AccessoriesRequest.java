package com.cour.micromania.dto;

import com.cour.micromania.model.Color;
import com.cour.micromania.model.State;

import java.time.LocalDate;
import java.util.List;

public record AccessoriesRequest(
        String name,
        List<Integer> compatibleConsoleIds,
        String brand,
        Float price,
        State state,
        LocalDate releaseDate,
        List<Color> color,
        int quantityInStock
) {
}
