package com.cour.micromania.controller;

import com.cour.micromania.dto.ShopRequest;
import com.cour.micromania.model.Shop;
import com.cour.micromania.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    public List<Shop> shops() {
        return shopService.getShops();
    }

    @GetMapping("/{id}")
    public Shop shops(@PathVariable int id) {
        return shopService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shop create(@RequestBody ShopRequest request) {
        return shopService.create(request);
    }

    @PutMapping("/{id}")
    public Shop update(@PathVariable int id, @RequestBody ShopRequest request) {
        return shopService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        shopService.delete(id);
    }
}
