package com.fiap.restaurant_management.aplication.gateway;

import com.fiap.restaurant_management.domain.entities.Customer;

import java.util.List;

public interface ICustomerRepository {

    Customer registerCustomer(Customer customer);

    List<Customer> listAllCustomers();
}
