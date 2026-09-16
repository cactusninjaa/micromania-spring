package com.cour.micromania.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Accessories implements Product {
    private static int CPT_ID = 1;
    private final int id;
    private String name;
    private List<Console> compatibleConsole;
    private String brand;
    private Float price;
    private State state;
    private Date releaseDate;
    private int quantityInStock;
    private List<Color> color;

    public Accessories(String name, List<Console> compatibleConsole, String brand, Float price, State state, Date releaseDate, List<Color> color, int quantity) {
        this.id = CPT_ID++;
        this.name = name;
        this.compatibleConsole = compatibleConsole;
        this.brand = brand;
        this.price = price;
        this.state = state;
        this.releaseDate = releaseDate;
        this.quantityInStock = quantity;
        this.color = color;
    }
}
