package com.fiap.restaurant_management.aplication.usecases;

import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.domain.entities.Customer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListCustomer {

    private final ICustomerRepository repository;

    public List<Customer> listAllCustomers() {
        return this.repository.listAllCustomers();
    }
}
