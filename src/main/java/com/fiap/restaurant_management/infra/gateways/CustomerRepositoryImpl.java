package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.infra.mapper.CustomerEntityMapper;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements ICustomerRepository {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper mapper;

    @Override
    public Customer registerCustomer(Customer customer) {
        CustomerEntity customerEntity = mapper.toEntity(customer);
        customerRepository.save(customerEntity);
        return mapper.toCustomer(customerEntity);
    }


}
