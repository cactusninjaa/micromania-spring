package com.cour.micromania.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Console implements Product {
    private static int CPT_ID = 1;
    private final int id;
    private String name;
    private String brand;
    private Float price;
    private State state;
    private Date releaseDate;
    private int quantityInStock;
    private List<Color> colors;

    public Console(String name, String brand, Float price, State state, Date releaseDate, int quantity, List<Color> colors) {
        this.id = CPT_ID++;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.state = state;
        this.releaseDate = releaseDate;
        this.quantityInStock = quantity;
        this.colors = colors;
    }
}
