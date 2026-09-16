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
public class Console extends Product {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "console_color",
            joinColumns = @JoinColumn(name = "console_id"))
    @Column(name = "color")
    private List<Color> colors;

    public Console(String name, String brand, Float price, State state, LocalDate releaseDate, int quantity, List<Color> colors) {
        super(name, brand, price, state, releaseDate, quantity);
        this.colors = colors;
    }
}
