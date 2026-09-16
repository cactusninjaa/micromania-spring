package com.cour.micromania.model;

import com.cour.micromania.service.ProductService;

import java.util.Date;
import java.util.List;

import static java.lang.Math.round;

public class Console implements ProductService {
    private static int CPT_ID = 1;
    private final int id;
    private String name;
    private String brand;
    private Float price;
    private State state;
    private final Date releaseDate;
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

    public int sell(int quantity, Customer customer) throws IllegalArgumentException {
        int quantityAfterSell = this.quantityInStock - quantity;
        if (quantityAfterSell < 1) {
            throw new IllegalArgumentException("Not enough product in stock");
        }
        if (customer.isHasFidelity()) {
            customer.setFidelityPoint(price);
        }
        return this.quantityInStock = quantityAfterSell;
    };

    public int buy(int quantity) {
        return this.quantityInStock += quantity;
    };

    public float getReselPrice(State state) throws IllegalArgumentException {
        if (state == State.BROKEN) {
            return Float.intBitsToFloat(0);
        } else if (state == State.STOLEN) {
            throw new IllegalArgumentException("Stolen product can't be buy");
        } else {
            return round(this.price * 0.6);
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }
}
