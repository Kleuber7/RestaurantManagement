package com.fiap.restaurant_management.infra.persistence;

import com.fiap.restaurant_management.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
