package com.cour.micromania.controller;

import com.cour.micromania.dto.AccessoriesRequest;
import com.cour.micromania.model.Accessories;
import com.cour.micromania.service.AccessoriesService;
import com.cour.micromania.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/accessories")
public class AccessoriesController {
    private final AccessoriesService accessoriesService;
    private final CustomerService customerService;

    @GetMapping
    public List<Accessories> accessories() {
        return accessoriesService.getAccessories();
    }

    @GetMapping("/{id}")
    public Accessories accessories(@PathVariable int id) {
        return accessoriesService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Accessories create(@RequestBody AccessoriesRequest request) {
        return accessoriesService.create(request);
    }

    @PutMapping("/{id}")
    public Accessories update(@PathVariable int id, @RequestBody AccessoriesRequest request) {
        return accessoriesService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        accessoriesService.delete(id);
    }

    @GetMapping("/{id}/resel-price")
    public float reselPrice(@PathVariable int id) {
        return accessoriesService.getReselPrice(id);
    }

    @PostMapping("/{id}/sell")
    public int sell(@PathVariable int id, @RequestParam int quantity, @RequestParam int customerId) {
        return accessoriesService.sell(id, quantity, customerService.findById(customerId));
    }

    @PostMapping("/{id}/buy")
    public int buy(@PathVariable int id, @RequestParam int quantity) {
        return accessoriesService.buy(id, quantity);
    }
}
