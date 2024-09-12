package com.fiap.restaurant_management.aplication.gateway;

import com.fiap.restaurant_management.domain.entities.Customer;

public interface ICustomerRepository {

    Customer registerCustomer(Customer customer);

}
