package com.fiap.restaurant_management.aplication.usecases.customer;

import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.domain.entities.Customer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCustomer {

    private final ICustomerRepository repository;

    public Customer registerCustomer(Customer customer) {
        return repository.registerCustomer(customer);
    }


}
