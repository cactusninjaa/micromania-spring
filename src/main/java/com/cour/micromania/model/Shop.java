package com.cour.micromania.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Shop {
    private static int CPT_ID = 1;
    private final int id;

    private int zipCode;
    private String city;
    private String streetName;
    private int streetNumber;

    private List<Product> productInStock = new ArrayList<>();
    private boolean isOpen = false;
    private List<Employee> employees = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private Float monitaryBase;

    public Shop(int zipCode, String city, String streetName, int streetNumber, List<Employee> employees, List<Customer> customers, Float monitaryBase) {
        this.id = CPT_ID++;
        this.zipCode = zipCode;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.employees = employees;
        this.customers = customers;
        this.monitaryBase = monitaryBase;
    }
}
