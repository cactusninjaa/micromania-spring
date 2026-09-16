package com.cour.micromania.service;

import com.cour.micromania.model.State;
import org.springframework.stereotype.Service;

import static java.lang.Math.round;

@Service
public class AccessoriesService {

    public float getReselPrice(State state) throws IllegalArgumentException {
        if (state == State.BROKEN) {
            return Float.intBitsToFloat(0);
        } else if (state == State.STOLEN) {
            throw new IllegalArgumentException("Stolen product can't be buy");
        } else {
            return round(this.price * 0.6);
        }
    };


    public int buy(int quantity) {
        return this.quantityInStock += quantity;
    };

}
