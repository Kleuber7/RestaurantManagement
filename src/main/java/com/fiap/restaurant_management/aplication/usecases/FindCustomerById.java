package com.fiap.restaurant_management.aplication.usecases;

import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.domain.entities.Customer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindCustomerById {

    private final ICustomerRepository repository;

    public Customer searchCustomerById(Long id) {
       var customer =  repository.findCustomerById(id);
       return customer;
    }
}
