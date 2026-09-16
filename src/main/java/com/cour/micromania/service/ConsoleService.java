package com.cour.micromania.service;

import com.cour.micromania.dto.ConsoleRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Console;
import com.cour.micromania.model.Customer;
import com.cour.micromania.model.State;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.round;

@Service
@Getter
public class ConsoleService implements ProductService {

    private final List<Console> consoles = new ArrayList<>();

    public Console findById(int id) {
        return consoles.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Console not found"));
    }

    public List<Console> findAllByIds(List<Integer> ids) {
        if (ids == null) {
            return new ArrayList<>();
        }
        return ids.stream()
                .map(this::findById)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Console create(ConsoleRequest request) {
        Console console = new Console(
                request.name(),
                request.brand(),
                request.price(),
                request.state(),
                request.releaseDate(),
                request.quantityInStock(),
                request.colors()
        );
        consoles.add(console);
        return console;
    }

    public Console update(int id, ConsoleRequest request) {
        Console console = findById(id);
        console.setName(request.name());
        console.setBrand(request.brand());
        console.setPrice(request.price());
        console.setState(request.state());
        console.setReleaseDate(request.releaseDate());
        console.setQuantityInStock(request.quantityInStock());
        console.setColors(request.colors());
        return console;
    }

    public void delete(int id) {
        if (!consoles.remove(findById(id))) {
            throw new NotFoundException("Console not found");
        }
    }

    public float getReselPrice(int id) throws IllegalArgumentException {
        Console console = findById(id);
        if (console.getState() == State.BROKEN) {
            return 0f;
        } else if (console.getState() == State.STOLEN) {
            throw new IllegalArgumentException("Stolen product can't be buy");
        } else {
            return round(console.getPrice() * 0.6);
        }
    }

    public int sell(int id, int quantity, Customer customer) throws IllegalArgumentException {
        Console console = findById(id);
        int quantityAfterSell = console.getQuantityInStock() - quantity;
        if (quantityAfterSell < 0) {
            throw new IllegalArgumentException("Not enough product in stock");
        }
        if (customer.isHasFidelity()) {
            customer.setFidelityPoint(console.getPrice());
        }
        console.setQuantityInStock(quantityAfterSell);
        customer.getBuyHistory().add(console);
        return quantityAfterSell;
    }

    public int buy(int id, int quantity) {
        Console console = findById(id);
        int newQuantity = console.getQuantityInStock() + quantity;
        console.setQuantityInStock(newQuantity);
        return newQuantity;
    }
}
