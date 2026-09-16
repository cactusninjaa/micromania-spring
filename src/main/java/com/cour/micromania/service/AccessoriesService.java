package com.cour.micromania.service;

import com.cour.micromania.dto.AccessoriesRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Accessories;
import com.cour.micromania.model.Customer;
import com.cour.micromania.model.State;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

@Service
@AllArgsConstructor
public class AccessoriesService implements ProductService {

    private final List<Accessories> accessories = new ArrayList<>();
    private final ConsoleService consoleService;

    public List<Accessories> getAccessories() {
        return accessories;
    }

    public Accessories findById(int id) {
        return accessories.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Accessory not found"));
    }

    public Accessories create(AccessoriesRequest request) {
        Accessories accessory = new Accessories(
                request.name(),
                consoleService.findAllByIds(request.compatibleConsoleIds()),
                request.brand(),
                request.price(),
                request.state(),
                request.releaseDate(),
                request.color(),
                request.quantityInStock()
        );
        accessories.add(accessory);
        return accessory;
    }

    public Accessories update(int id, AccessoriesRequest request) {
        Accessories accessory = findById(id);
        accessory.setName(request.name());
        accessory.setCompatibleConsole(consoleService.findAllByIds(request.compatibleConsoleIds()));
        accessory.setBrand(request.brand());
        accessory.setPrice(request.price());
        accessory.setState(request.state());
        accessory.setReleaseDate(request.releaseDate());
        accessory.setColor(request.color());
        accessory.setQuantityInStock(request.quantityInStock());
        return accessory;
    }

    public void delete(int id) {
        if (!accessories.remove(findById(id))) {
            throw new NotFoundException("Accessory not found");
        }
    }

    public float getReselPrice(int id) throws IllegalArgumentException {
        Accessories accessory = findById(id);
        if (accessory.getState() == State.BROKEN) {
            return 0f;
        } else if (accessory.getState() == State.STOLEN) {
            throw new IllegalArgumentException("Stolen product can't be buy");
        } else {
            return round(accessory.getPrice() * 0.6);
        }
    }

    public int sell(int id, int quantity, Customer customer) throws IllegalArgumentException {
        Accessories accessory = findById(id);
        int quantityAfterSell = accessory.getQuantityInStock() - quantity;
        if (quantityAfterSell < 0) {
            throw new IllegalArgumentException("Not enough product in stock");
        }
        if (customer.isHasFidelity()) {
            customer.setFidelityPoint(accessory.getPrice());
        }
        accessory.setQuantityInStock(quantityAfterSell);
        customer.getBuyHistory().add(accessory);
        return quantityAfterSell;
    }

    public int buy(int id, int quantity) {
        Accessories accessory = findById(id);
        int newQuantity = accessory.getQuantityInStock() + quantity;
        accessory.setQuantityInStock(newQuantity);
        return newQuantity;
    }
}
