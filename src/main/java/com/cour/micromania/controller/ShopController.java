package com.cour.micromania.controller;

import com.cour.micromania.dto.ErrorResponse;
import com.cour.micromania.dto.ShopRequest;
import com.cour.micromania.model.Shop;
import com.cour.micromania.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/shops", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Magasins", description = "Gestion des magasins, de leurs employes et de leurs clients")
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    @Operation(summary = "Lister les magasins")
    @ApiResponse(responseCode = "200", description = "Liste renvoyee")
    public List<Shop> shops() {
        return shopService.getShops();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer un magasin par son id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Magasin trouve"),
            @ApiResponse(responseCode = "404", description = "Magasin introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Shop shops(@PathVariable int id) {
        return shopService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un magasin")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Magasin cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Une reference fournie est introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Shop create(@RequestBody ShopRequest request) {
        return shopService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour un magasin")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Magasin mis a jour"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Magasin introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Shop update(@PathVariable int id, @RequestBody ShopRequest request) {
        return shopService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un magasin")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Magasin supprime"),
            @ApiResponse(responseCode = "404", description = "Magasin introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Encore reference ailleurs, suppression impossible", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable int id) {
        shopService.delete(id);
    }
}
