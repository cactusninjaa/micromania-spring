package com.cour.micromania.controller;

import com.cour.micromania.dto.ErrorResponse;
import com.cour.micromania.dto.CustomerRequest;
import com.cour.micromania.model.Customer;
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
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Clients", description = "Gestion des clients, de leur fidelite et de leur historique d'achat")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Lister les clients")
    @ApiResponse(responseCode = "200", description = "Liste renvoyee")
    public List<Customer> customers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperer un client par son id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouve"),
            @ApiResponse(responseCode = "404", description = "Client introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Customer customers(@PathVariable int id) {
        return customerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Une reference fournie est introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Customer create(@RequestBody CustomerRequest request) {
        return customerService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre a jour un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client mis a jour"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Client introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Customer update(@PathVariable int id, @RequestBody CustomerRequest request) {
        return customerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprime"),
            @ApiResponse(responseCode = "404", description = "Client introuvable", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Encore reference ailleurs, suppression impossible", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public void delete(@PathVariable int id) {
        customerService.delete(id);
    }
}
