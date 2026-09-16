package com.cour.micromania.service;

import com.cour.micromania.dto.ShopRequest;
import com.cour.micromania.exception.NotFoundException;
import com.cour.micromania.model.Shop;
import com.cour.micromania.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

    public Shop findById(int id) {
        return shopRepository.findById(id)
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
        return shopRepository.save(shop);
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
        return shopRepository.save(shop);
    }

    public void delete(int id) {
        shopRepository.delete(findById(id));
    }
}
