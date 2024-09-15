package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.infra.mapper.CustomerMapper;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements ICustomerRepository {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Override
    public Customer registerCustomer(Customer customer) {
        CustomerEntity customerEntity = mapper.toEntity(customer);
        customerRepository.save(customerEntity);
        return mapper.toCustomer(customerEntity);
    }

    @Override
    public Customer findCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findByCustomerCode(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return mapper.toCustomer(customerEntity);
    }

    @Override
    public Boolean existsCustomerById(Long code) {
        return customerRepository.existsById(code);
    }


}
