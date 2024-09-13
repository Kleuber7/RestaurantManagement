package com.fiap.restaurant_management.infra.persistence.repository;

import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCustomerCode(Long id);
}
