package com.cour.micromania.service;

import com.cour.micromania.dto.ShopRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {

    @Getter
    private final List<Shop> shops = new ArrayList<>();
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    public Shop findById(int id) {
        return shops.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Shop not found"));
    }

    public Shop create(ShopRequest request) {
        Shop shop = new Shop(
                request.zipCode(),
                request.city(),
                request.streetName(),
                request.streetNumber(),
                employeeService.findAllByIds(request.employeeIds()),
                customerService.findAllByIds(request.customerIds()),
                request.monitaryBase()
        );
        shop.setOpen(request.open());
        shops.add(shop);
        return shop;
    }

    public Shop update(int id, ShopRequest request) {
        Shop shop = findById(id);
        shop.setZipCode(request.zipCode());
        shop.setCity(request.city());
        shop.setStreetName(request.streetName());
        shop.setStreetNumber(request.streetNumber());
        shop.setEmployees(employeeService.findAllByIds(request.employeeIds()));
        shop.setCustomers(customerService.findAllByIds(request.customerIds()));
        shop.setMonitaryBase(request.monitaryBase());
        shop.setOpen(request.open());
        return shop;
    }

    public void delete(int id) {
        if (!shops.remove(findById(id))) {
            throw new NotFoundException("Shop not found");
        }
    }
}
