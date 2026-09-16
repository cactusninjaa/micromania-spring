package com.cour.micromania.model;

import java.util.Date;

/**
 * Tout ce qui peut etre vendu / rachete en magasin.
 */
public interface Product {
    int getId();

    String getName();

    Float getPrice();

    State getState();

    Date getReleaseDate();

    int getQuantityInStock();

    void setQuantityInStock(int quantityInStock);
}
