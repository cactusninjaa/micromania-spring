package com.cour.micromania.controller;

import com.cour.micromania.dto.ConsoleRequest;
import com.cour.micromania.model.Console;
import com.cour.micromania.service.ConsoleService;
import com.cour.micromania.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/consoles")
public class ConsoleController {
    private final ConsoleService consoleService;
    private final CustomerService customerService;

    @GetMapping
    public List<Console> consoles() {
        return consoleService.getConsoles();
    }

    @GetMapping("/{id}")
    public Console consoles(@PathVariable int id) {
        return consoleService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Console create(@RequestBody ConsoleRequest request) {
        return consoleService.create(request);
    }

    @PutMapping("/{id}")
    public Console update(@PathVariable int id, @RequestBody ConsoleRequest request) {
        return consoleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        consoleService.delete(id);
    }

    @GetMapping("/{id}/resel-price")
    public float reselPrice(@PathVariable int id) {
        return consoleService.getReselPrice(id);
    }

    @PostMapping("/{id}/sell")
    public int sell(@PathVariable int id, @RequestParam int quantity, @RequestParam int customerId) {
        return consoleService.sell(id, quantity, customerService.findById(customerId));
    }

    @PostMapping("/{id}/buy")
    public int buy(@PathVariable int id, @RequestParam int quantity) {
        return consoleService.buy(id, quantity);
    }
}
