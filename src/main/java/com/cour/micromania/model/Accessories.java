package com.cour.micromania.model;

import java.util.Date;
import java.util.List;

import static java.lang.Math.round;


public class Accessories implements Product{
    private static int CPT_ID = 1;
    private final int id;
    String name;
    List<Console> compatibleConsole;
    String brand;
    Float price;
    State state;
    Date releaseDate;
    int quantityInStock;
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

    public List<Console> getCompatibleConsole() {
        return compatibleConsole;
    }

    public void setCompatibleConsole(List<Console> compatibleConsole) {
        this.compatibleConsole = compatibleConsole;
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

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }
}
