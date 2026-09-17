package com.cour.micromania.controller;

import com.cour.micromania.dto.ErrorResponse;
import com.cour.micromania.dto.VideoGameRequest;
import com.cour.micromania.model.VideoGame;
import com.cour.micromania.service.VideoGameService;
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
@RequestMapping(value = "/video-games", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Jeux video", description = "Gestion des jeux video et operations de vente/rachat")
public class VideoGameController {
    private final VideoGameService videoGameService;
    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Lister les jeux video")
    @ApiResponse(responseCode = "200", description = "Liste renvoyee")
    public List<VideoGame> videoGames() {
        return videoGameService.getVideoGames();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer un jeu video par son id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jeu video trouve"),
            @ApiResponse(responseCode = "404", description = "Jeu video introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public VideoGame videoGames(@PathVariable int id) {
        return videoGameService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un jeu video")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Jeu video cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Une reference fournie est introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public VideoGame create(@RequestBody VideoGameRequest request) {
        return videoGameService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour un jeu video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jeu video mis a jour"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Jeu video introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public VideoGame update(@PathVariable int id, @RequestBody VideoGameRequest request) {
        return videoGameService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un jeu video")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Jeu video supprime"),
            @ApiResponse(responseCode = "404", description = "Jeu video introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Encore reference ailleurs, suppression impossible", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable int id) {
        videoGameService.delete(id);
    }

    @GetMapping("/{id}/resel-price")
    @Operation(summary = "Prix de rachat d'occasion (60% du prix neuf)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prix de rachat calcule"),
            @ApiResponse(responseCode = "400", description = "Produit vole : rachat interdit", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Jeu video introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public float reselPrice(@PathVariable int id) {
        return videoGameService.getReselPrice(id);
    }

    @PostMapping("/{id}/sell")
    @Operation(summary = "Vendre a un client", description = "Decremente le stock, cree les points de fidelite et complete l'historique d'achat du client.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vente effectuee, stock restant renvoye"),
            @ApiResponse(responseCode = "400", description = "Stock insuffisant, ou client trop jeune pour ce jeu", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Jeu video ou client introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public int sell(@PathVariable int id, @RequestParam int quantity, @RequestParam int customerId) {
        return videoGameService.sell(id, quantity, customerService.findById(customerId));
    }

    @PostMapping("/{id}/buy")
    @Operation(summary = "Reprendre du stock", description = "Incremente la quantite en stock.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock mis a jour, nouvelle quantite renvoyee"),
            @ApiResponse(responseCode = "404", description = "Jeu video introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public int buy(@PathVariable int id, @RequestParam int quantity) {
        return videoGameService.buy(id, quantity);
    }
}
