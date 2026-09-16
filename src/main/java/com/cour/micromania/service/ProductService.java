package com.cour.micromania.service;

import com.cour.micromania.model.Customer;
import com.cour.micromania.model.State;

public interface ProductService {
    int sell(int quantity, Customer customer);
    int buy(int quantity);
    float getReselPrice(State state);
}
