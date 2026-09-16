package com.cour.micromania.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Tout ce qui peut etre vendu / rachete en magasin.
 * Table par sous-classe (JOINED) : une table PRODUCT pour les champs communs,
 * une table par type de produit pour le reste.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String brand;

    private Float price;

    @Enumerated(EnumType.STRING)
    private State state;

    private LocalDate releaseDate;

    private int quantityInStock;

    protected Product(String name, String brand, Float price, State state, LocalDate releaseDate, int quantityInStock) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.state = state;
        this.releaseDate = releaseDate;
        this.quantityInStock = quantityInStock;
    }
}
