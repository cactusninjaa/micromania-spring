package com.cour.micromania.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static java.lang.Math.round;

@Getter
@Setter
public class Accessories{
    private static int CPT_ID = 1;
    private final int id;
    String name;
    List<Console> compatibleConsole;
    String brand;
    Float price;
    State state;
    Date releaseDate;
    @Getter @Setter int quantityInStock;
    List<Color> color;

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
