package com.cour.micromania.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.round;

@Getter
@Setter
public class Customer {
    private static int CPT_ID = 1;
    private final int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private List<Product> buyHistory = new ArrayList<>();
    private boolean hasFidelity = false;
    @Setter(AccessLevel.NONE)
    private int fidelityPoint;

    public Customer(Date birthDate, String firstName, String lastName, String email) {
        this.id = CPT_ID++;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getAge() {
        LocalDate birth = this.birthDate.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birth, LocalDate.now()).getYears();
    }

    public void setFidelityPoint(float price) {
        this.fidelityPoint += round(price * 100);
    }
}
