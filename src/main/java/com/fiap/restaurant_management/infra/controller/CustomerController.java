package com.fiap.restaurant_management.infra.controller;

import com.fiap.restaurant_management.aplication.usecases.CreateCustomer;
import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.infra.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomer createCustomer;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomers(@RequestBody CustomerDto dto) {
        Customer customerDomain = createCustomer.registerCustomer(new Customer(dto.name(), dto.email(), dto.phone()));

        var customerDto = new CustomerDto(customerDomain.getName(), customerDomain.getEmail(), customerDomain.getPhone());

        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }
}