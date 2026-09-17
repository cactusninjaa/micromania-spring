package com.cour.micromania.controller;

import com.cour.micromania.dto.ErrorResponse;
import com.cour.micromania.dto.ConsoleRequest;
import com.cour.micromania.model.Console;
import com.cour.micromania.service.ConsoleService;
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
@RequestMapping(value = "/consoles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Consoles", description = "Gestion des consoles et operations de vente/rachat")
public class ConsoleController {
    private final ConsoleService consoleService;
    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Lister les consoles")
    @ApiResponse(responseCode = "200", description = "Liste renvoyee")
    public List<Console> consoles() {
        return consoleService.getConsoles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer une console par son id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Console trouve"),
            @ApiResponse(responseCode = "404", description = "Console introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Console consoles(@PathVariable int id) {
        return consoleService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer une console")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Console cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Une reference fournie est introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Console create(@RequestBody ConsoleRequest request) {
        return consoleService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour une console")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Console mis a jour"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Console introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Console update(@PathVariable int id, @RequestBody ConsoleRequest request) {
        return consoleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une console")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Console supprime"),
            @ApiResponse(responseCode = "404", description = "Console introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Encore reference ailleurs, suppression impossible", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable int id) {
        consoleService.delete(id);
    }

    @GetMapping("/{id}/resel-price")
    @Operation(summary = "Prix de rachat d'occasion (60% du prix neuf)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prix de rachat calcule"),
            @ApiResponse(responseCode = "400", description = "Produit vole : rachat interdit", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Console introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public float reselPrice(@PathVariable int id) {
        return consoleService.getReselPrice(id);
    }

    @PostMapping("/{id}/sell")
    @Operation(summary = "Vendre a un client", description = "Decremente le stock, cree les points de fidelite et complete l'historique d'achat du client.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vente effectuee, stock restant renvoye"),
            @ApiResponse(responseCode = "400", description = "Stock insuffisant", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Console ou client introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public int sell(@PathVariable int id, @RequestParam int quantity, @RequestParam int customerId) {
        return consoleService.sell(id, quantity, customerService.findById(customerId));
    }

    @PostMapping("/{id}/buy")
    @Operation(summary = "Reprendre du stock", description = "Incremente la quantite en stock.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock mis a jour, nouvelle quantite renvoyee"),
            @ApiResponse(responseCode = "404", description = "Console introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public int buy(@PathVariable int id, @RequestParam int quantity) {
        return consoleService.buy(id, quantity);
    }
}
