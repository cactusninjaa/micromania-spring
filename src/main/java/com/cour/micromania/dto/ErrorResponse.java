package com.cour.micromania.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Corps renvoye par l'API en cas d'erreur")
public record ErrorResponse(
        @Schema(description = "Message d'erreur", example = "Accessory not found")
        String error
) {
}
