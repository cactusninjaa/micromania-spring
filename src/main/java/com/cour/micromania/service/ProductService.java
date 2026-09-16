package com.cour.micromania.service;

import com.cour.micromania.model.Customer;
import com.cour.micromania.model.State;

public interface ProductService {
    int sell(int id, int quantity, Customer customer);
    int buy(int id, int quantity);
    float getReselPrice(int id);
}
