package com.cour.micromania.model;

public interface Product {
    int sell(int quantity, Customer customer);
    int buy(int quantity);
    float getReselPrice(State state);
}
