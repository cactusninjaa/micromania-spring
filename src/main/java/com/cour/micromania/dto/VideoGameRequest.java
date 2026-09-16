package com.cour.micromania.dto;

import com.cour.micromania.model.Languages;
import com.cour.micromania.model.Saga;
import com.cour.micromania.model.State;
import com.cour.micromania.model.Type;

import java.util.Date;
import java.util.List;

public record VideoGameRequest(
        String name,
        String description,
        List<Integer> compatibleConsoleIds,
        String brand,
        Float price,
        Saga saga,
        List<Type> type,
        State state,
        Date releaseDate,
        int minimumAgeToPlay,
        List<Languages> languages,
        int quantityInStock
) {
}
