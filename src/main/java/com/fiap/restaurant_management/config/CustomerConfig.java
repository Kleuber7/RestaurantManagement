package com.fiap.restaurant_management.config;

import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.aplication.usecases.CreateCustomer;
import com.fiap.restaurant_management.aplication.usecases.FindCustomerById;
import com.fiap.restaurant_management.infra.mapper.CustomerMapper;
import com.fiap.restaurant_management.infra.gateways.CustomerRepositoryImpl;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.fiap.restaurant_management.infra.persistence.repository")
public class CustomerConfig {

    @Bean
    CreateCustomer createCustomer(ICustomerRepository customerRepository) {
        return new CreateCustomer(customerRepository);
    }

    @Bean
    CustomerRepositoryImpl customCustomerRepository(CustomerRepository customerRepository, CustomerMapper customerEntityMapper) {
        return new CustomerRepositoryImpl(customerRepository, customerEntityMapper);
    }

    @Bean
    CustomerMapper customerEntityMapper() {
        return new CustomerMapper();
    }

    @Bean
    FindCustomerById searchCustomerById(ICustomerRepository customerRepository) {
        return new FindCustomerById(customerRepository);
    }



}
