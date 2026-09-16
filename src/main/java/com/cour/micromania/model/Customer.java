package com.cour.micromania.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_buy_history",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> buyHistory = new ArrayList<>();

    private boolean hasFidelity = false;

    @Setter(AccessLevel.NONE)
    private int fidelityPoint;

    public Customer(LocalDate birthDate, String firstName, String lastName, String email) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Transient
    public int getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public void setFidelityPoint(float price) {
        this.fidelityPoint += round(price * 100);
    }
}
