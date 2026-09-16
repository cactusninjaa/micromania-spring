package com.cour.micromania.controller;

import com.cour.micromania.dto.VideoGameRequest;
import com.cour.micromania.model.VideoGame;
import com.cour.micromania.service.CustomerService;
import com.cour.micromania.service.VideoGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/video-games")
public class VideoGameController {
    private final VideoGameService videoGameService;
    private final CustomerService customerService;

    @GetMapping
    public List<VideoGame> videoGames() {
        return videoGameService.getVideoGames();
    }

    @GetMapping("/{id}")
    public VideoGame videoGames(@PathVariable int id) {
        return videoGameService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoGame create(@RequestBody VideoGameRequest request) {
        return videoGameService.create(request);
    }

    @PutMapping("/{id}")
    public VideoGame update(@PathVariable int id, @RequestBody VideoGameRequest request) {
        return videoGameService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        videoGameService.delete(id);
    }

    @GetMapping("/{id}/resel-price")
    public float reselPrice(@PathVariable int id) {
        return videoGameService.getReselPrice(id);
    }

    @PostMapping("/{id}/sell")
    public int sell(@PathVariable int id, @RequestParam int quantity, @RequestParam int customerId) {
        return videoGameService.sell(id, quantity, customerService.findById(customerId));
    }

    @PostMapping("/{id}/buy")
    public int buy(@PathVariable int id, @RequestParam int quantity) {
        return videoGameService.buy(id, quantity);
    }
}
