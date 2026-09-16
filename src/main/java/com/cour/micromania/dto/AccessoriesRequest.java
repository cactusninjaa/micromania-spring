package com.cour.micromania.dto;

import com.cour.micromania.model.Color;
import com.cour.micromania.model.State;

import java.util.Date;
import java.util.List;

public record AccessoriesRequest(
        String name,
        List<Integer> compatibleConsoleIds,
        String brand,
        Float price,
        State state,
        Date releaseDate,
        List<Color> color,
        int quantityInStock
) {
}
