package com.fiap.restaurant_management.infra.mapper;

import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;

public class CustomerMapper {

    public CustomerEntity toEntity(Customer customer) {
        return new CustomerEntity(
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }

    public Customer toCustomer(CustomerEntity customerEntity) {
        return new Customer(
                customerEntity.getCustomerCode(),
                customerEntity.getName(),
                customerEntity.getEmail(),
                customerEntity.getPhone()
        );
    }

}
