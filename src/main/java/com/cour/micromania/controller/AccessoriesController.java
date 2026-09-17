package com.cour.micromania.controller;

import com.cour.micromania.dto.ErrorResponse;
import com.cour.micromania.dto.AccessoriesRequest;
import com.cour.micromania.model.Accessories;
import com.cour.micromania.service.AccessoriesService;
import com.cour.micromania.service.CustomerService;
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
@RequestMapping(value = "/accessories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Accessoires", description = "Gestion des accessoires et operations de vente/rachat")
public class AccessoriesController {
    private final AccessoriesService accessoriesService;
    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Lister les accessoires")
    @ApiResponse(responseCode = "200", description = "Liste renvoyee")
    public List<Accessories> accessories() {
        return accessoriesService.getAccessories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer un accessoire par son id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accessoire trouve"),
            @ApiResponse(responseCode = "404", description = "Accessoire introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Accessories accessories(@PathVariable int id) {
        return accessoriesService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un accessoire")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Accessoire cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Une reference fournie est introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Accessories create(@RequestBody AccessoriesRequest request) {
        return accessoriesService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour un accessoire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accessoire mis a jour"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Accessoire introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Accessories update(@PathVariable int id, @RequestBody AccessoriesRequest request) {
        return accessoriesService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un accessoire")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Accessoire supprime"),
            @ApiResponse(responseCode = "404", description = "Accessoire introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Encore reference ailleurs, suppression impossible", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable int id) {
        accessoriesService.delete(id);
    }

    @GetMapping("/{id}/resel-price")
    @Operation(summary = "Prix de rachat d'occasion (60% du prix neuf)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prix de rachat calcule"),
            @ApiResponse(responseCode = "400", description = "Produit vole : rachat interdit", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Accessoire introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public float reselPrice(@PathVariable int id) {
        return accessoriesService.getReselPrice(id);
    }

    @PostMapping("/{id}/sell")
    @Operation(summary = "Vendre a un client", description = "Decremente le stock, cree les points de fidelite et complete l'historique d'achat du client.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vente effectuee, stock restant renvoye"),
            @ApiResponse(responseCode = "400", description = "Stock insuffisant", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Accessoire ou client introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public int sell(@PathVariable int id, @RequestParam int quantity, @RequestParam int customerId) {
        return accessoriesService.sell(id, quantity, customerService.findById(customerId));
    }

    @PostMapping("/{id}/buy")
    @Operation(summary = "Reprendre du stock", description = "Incremente la quantite en stock.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock mis a jour, nouvelle quantite renvoyee"),
            @ApiResponse(responseCode = "404", description = "Accessoire introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public int buy(@PathVariable int id, @RequestParam int quantity) {
        return accessoriesService.buy(id, quantity);
    }
}
