package com.cour.micromania.model;

import com.cour.micromania.service.ProductService;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static java.lang.Math.round;

public class Customer {
    private static int CPT_ID = 1;
    private final int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private List<ProductService> buyHistory;
    private boolean hasFidelity = false;
    private int fidelityPoint;

    public Customer(Date birthDate, String firstName, String lastName, String email) {
        this.id = CPT_ID++;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        LocalDate birth = this.getBirthDate().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birth, LocalDate.now()).getYears();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProductService> getBuyHistory() {
        return buyHistory;
    }

    public void setBuyHistory(List<ProductService> buyHistory) {
        this.buyHistory = buyHistory;
    }

    public boolean isHasFidelity() {
        return hasFidelity;
    }

    public void setHasFidelity(boolean hasFidelity) {
        this.hasFidelity = hasFidelity;
    }

    public int getFidelityPoint() {
        return fidelityPoint;
    }

    public void setFidelityPoint(float price) {
        this.fidelityPoint += round(price * 100) ;
    }
}
