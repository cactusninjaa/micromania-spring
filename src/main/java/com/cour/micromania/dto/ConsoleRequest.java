package com.cour.micromania.dto;

import com.cour.micromania.model.Color;
import com.cour.micromania.model.State;

import java.util.Date;
import java.util.List;

public record ConsoleRequest(
        String name,
        String brand,
        Float price,
        State state,
        Date releaseDate,
        int quantityInStock,
        List<Color> colors
) {
}
