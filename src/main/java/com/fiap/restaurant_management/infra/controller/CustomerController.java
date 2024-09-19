package com.fiap.restaurant_management.infra.controller;

import com.fiap.restaurant_management.aplication.usecases.customer.CreateCustomer;
import com.fiap.restaurant_management.aplication.usecases.customer.FindCustomerById;
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
    private final FindCustomerById findCustomerByIdUseCase;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomers(@RequestBody CustomerDto dto) {
        Customer customerDomain = createCustomer.registerCustomer(new Customer(dto.name(), dto.email(), dto.phone()));

        var customerDto = new CustomerDto(customerDomain.getName(), customerDomain.getEmail(), customerDomain.getPhone());

        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id) {
        Customer customer = findCustomerByIdUseCase.searchCustomerById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomerDto(customer.getName(), customer.getEmail(), customer.getPhone()));
    }
}
