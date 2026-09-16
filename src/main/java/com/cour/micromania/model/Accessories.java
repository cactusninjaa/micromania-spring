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
public class Accessories extends Product {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "accessories_console",
            joinColumns = @JoinColumn(name = "accessories_id"),
            inverseJoinColumns = @JoinColumn(name = "console_id"))
    private List<Console> compatibleConsole;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "accessories_color",
            joinColumns = @JoinColumn(name = "accessories_id"))
    @Column(name = "color")
    private List<Color> color;

    public Accessories(String name, List<Console> compatibleConsole, String brand, Float price, State state, LocalDate releaseDate, List<Color> color, int quantity) {
        super(name, brand, price, state, releaseDate, quantity);
        this.compatibleConsole = compatibleConsole;
        this.color = color;
    }
}
