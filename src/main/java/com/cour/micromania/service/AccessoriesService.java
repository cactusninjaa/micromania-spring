package com.cour.micromania.service;

import com.cour.micromania.model.Accessories;
import com.cour.micromania.model.Customer;
import com.cour.micromania.model.State;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.Math.round;

@Service
@AllArgsConstructor
public class AccessoriesService implements ProductService {

    private final Accessories accessories;

    public float getReselPrice(State state) throws IllegalArgumentException {
        if (state == State.BROKEN) {
            return Float.intBitsToFloat(0);
        } else if (state == State.STOLEN) {
            throw new IllegalArgumentException("Stolen product can't be buy");
        } else {
            return round(accessories.getPrice() * 0.6);
        }
    };

    public int sell(int quantity, Customer customer) throws IllegalArgumentException {
        int quantityAfterSell = accessories.getQuantityInStock() - quantity;
        if (quantityAfterSell < 1) {
            throw new IllegalArgumentException("Not enough product in stock");
        }
        if (customer.isHasFidelity()) {
            customer.setFidelityPoint(accessories.getPrice());
        }
        return this.accessories.setQuantityInStock(quantityAfterSell);
    };

    public int buy(int quantity) {
        return accessories.getQuantityInStock() += quantity;
    };


    public int buy(int quantity) {
        return this.accessories.setQuantityInStock(this.accessories.getQuantityInStock() += quantity;);
    };

}
