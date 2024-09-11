package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.infra.persistence.CustomerEntity;

public class CustomerEntityMapper {

    public CustomerEntity toEntity(Customer customer) {
        return new CustomerEntity(
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }

    public Customer toCustomer(CustomerEntity customerEntity) {
        return new Customer(
                customerEntity.getName(),
                customerEntity.getEmail(),
                customerEntity.getPhone()
        );
    }

}
