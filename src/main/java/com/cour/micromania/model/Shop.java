package com.cour.micromania.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int zipCode;

    private String city;

    private String streetName;

    private int streetNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shop_product",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productInStock = new ArrayList<>();

    private boolean isOpen = false;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shop_employee",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shop_customer",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customers = new ArrayList<>();

    private Float monitaryBase;

    public Shop(int zipCode, String city, String streetName, int streetNumber, List<Employee> employees, List<Customer> customers, Float monitaryBase) {
        this.zipCode = zipCode;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.employees = employees;
        this.customers = customers;
        this.monitaryBase = monitaryBase;
    }
}
