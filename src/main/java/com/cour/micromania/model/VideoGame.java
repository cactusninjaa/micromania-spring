package com.cour.micromania.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VideoGame extends Product {

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "video_game_console",
            joinColumns = @JoinColumn(name = "video_game_id"),
            inverseJoinColumns = @JoinColumn(name = "console_id"))
    private List<Console> compatibleConsole;

    @Enumerated(EnumType.STRING)
    private Saga saga;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "video_game_type",
            joinColumns = @JoinColumn(name = "video_game_id"))
    @Column(name = "type")
    private List<Type> type;

    private int minimumAgeToPlay;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "video_game_language",
            joinColumns = @JoinColumn(name = "video_game_id"))
    @Column(name = "language")
    private List<Languages> languages;

    public VideoGame(String name, String description, List<Console> compatibleConsole, String brand, Float price, Saga saga, List<Type> type, State state, LocalDate releaseDate, int minimumAgeToPlay, List<Languages> languages, int quantity) {
        super(name, brand, price, state, releaseDate, quantity);
        this.description = description;
        this.compatibleConsole = compatibleConsole;
        this.saga = saga;
        this.type = type;
        this.minimumAgeToPlay = minimumAgeToPlay;
        this.languages = languages;
    }
}
