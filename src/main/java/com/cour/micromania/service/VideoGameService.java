package com.cour.micromania.service;

import com.cour.micromania.dto.VideoGameRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Customer;
import com.cour.micromania.model.State;
import com.cour.micromania.model.VideoGame;
import com.cour.micromania.repository.VideoGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.round;

@Service
@AllArgsConstructor
public class VideoGameService implements ProductService {

    private final VideoGameRepository videoGameRepository;
    private final ConsoleService consoleService;
    private final CustomerService customerService;

    public List<VideoGame> getVideoGames() {
        return videoGameRepository.findAll();
    }

    public VideoGame findById(int id) {
        return videoGameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Video game not found"));
    }

    public VideoGame create(VideoGameRequest request) {
        VideoGame videoGame = new VideoGame(
                request.name(),
                request.description(),
                consoleService.findAllByIds(request.compatibleConsoleIds()),
                request.brand(),
                request.price(),
                request.saga(),
                request.type(),
                request.state(),
                request.releaseDate(),
                request.minimumAgeToPlay(),
                request.languages(),
                request.quantityInStock()
        );
        return videoGameRepository.save(videoGame);
    }

    public VideoGame update(int id, VideoGameRequest request) {
        VideoGame videoGame = findById(id);
        videoGame.setName(request.name());
        videoGame.setDescription(request.description());
        videoGame.setCompatibleConsole(consoleService.findAllByIds(request.compatibleConsoleIds()));
        videoGame.setBrand(request.brand());
        videoGame.setPrice(request.price());
        videoGame.setSaga(request.saga());
        videoGame.setType(request.type());
        videoGame.setState(request.state());
        videoGame.setReleaseDate(request.releaseDate());
        videoGame.setMinimumAgeToPlay(request.minimumAgeToPlay());
        videoGame.setLanguages(request.languages());
        videoGame.setQuantityInStock(request.quantityInStock());
        return videoGameRepository.save(videoGame);
    }

    public void delete(int id) {
        videoGameRepository.delete(findById(id));
    }

    public float getReselPrice(int id) throws IllegalArgumentException {
        VideoGame videoGame = findById(id);
        if (videoGame.getState() == State.BROKEN) {
            return 0f;
        } else if (videoGame.getState() == State.STOLEN) {
            throw new IllegalArgumentException("Stolen product can't be buy");
        } else {
            return round(videoGame.getPrice() * 0.6);
        }
    }

    public int sell(int id, int quantity, Customer customer) throws IllegalArgumentException {
        VideoGame videoGame = findById(id);
        int quantityAfterSell = videoGame.getQuantityInStock() - quantity;
        if (quantityAfterSell < 0) {
            throw new IllegalArgumentException("Not enough product in stock");
        }
        if (customer.getAge() < videoGame.getMinimumAgeToPlay()) {
            throw new IllegalArgumentException("To young for to buy this game");
        }
        if (customer.isHasFidelity()) {
            customer.setFidelityPoint(videoGame.getPrice());
        }
        videoGame.setQuantityInStock(quantityAfterSell);
        videoGameRepository.save(videoGame);
        customer.getBuyHistory().add(videoGame);
        customerService.save(customer);
        return quantityAfterSell;
    }

    public int buy(int id, int quantity) {
        VideoGame videoGame = findById(id);
        int newQuantity = videoGame.getQuantityInStock() + quantity;
        videoGame.setQuantityInStock(newQuantity);
        videoGameRepository.save(videoGame);
        return newQuantity;
    }
}
