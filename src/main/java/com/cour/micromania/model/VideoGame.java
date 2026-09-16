package com.cour.micromania.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class VideoGame implements Product {
    private static int CPT_ID = 1;
    private final int id;
    private String name;
    private String description;
    private List<Console> compatibleConsole;
    private String brand;
    private Float price;
    private Saga saga;
    private List<Type> type;
    private State state;
    private Date releaseDate;
    private int minimumAgeToPlay;
    private List<Languages> languages;
    private int quantityInStock;

    public VideoGame(String name, String description, List<Console> compatibleConsole, String brand, Float price, Saga saga, List<Type> type, State state, Date releaseDate, int minimumAgeToPlay, List<Languages> languages, int quantity) {
        this.id = CPT_ID++;
        this.name = name;
        this.description = description;
        this.compatibleConsole = compatibleConsole;
        this.brand = brand;
        this.price = price;
        this.saga = saga;
        this.type = type;
        this.state = state;
        this.releaseDate = releaseDate;
        this.minimumAgeToPlay = minimumAgeToPlay;
        this.languages = languages;
        this.quantityInStock = quantity;
    }
}
