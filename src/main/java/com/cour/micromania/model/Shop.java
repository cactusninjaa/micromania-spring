package com.cour.micromania.model;

import com.cour.micromania.service.ProductService;

import java.util.List;

public class Shop {
    private static int CPT_ID = 1;
    private final int id;

    private int zipCode;
    private String city;
    private String streetName;
    private int streetNumber;

    private List<ProductService> productServiceInStock;
    private boolean isOpen = false;
    private List<Employee> employees;
    private List<Customer> customers;
    private Float monitaryBase;

    public Shop(int zipCode, String city, String streetName, int streetNumber, List<ProductService> productServiceInStock, List<Employee> employees, List<Customer> customers, Float monitaryBase) {
        this.id = CPT_ID++;
        this.zipCode = zipCode;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.productServiceInStock = productServiceInStock;
        this.employees = employees;
        this.customers = customers;
        this.monitaryBase = monitaryBase;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public List<ProductService> getProductInStock() {
        return productServiceInStock;
    }

    public void setProductInStock(List<ProductService> productServiceInStock) {
        this.productServiceInStock = productServiceInStock;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Float getMonitaryBase() {
        return monitaryBase;
    }

    public void setMonitaryBase(Float monitaryBase) {
        this.monitaryBase = monitaryBase;
    }
}
