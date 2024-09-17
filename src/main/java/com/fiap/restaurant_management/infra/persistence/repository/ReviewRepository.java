package com.fiap.restaurant_management.infra.persistence.repository;

import com.fiap.restaurant_management.infra.persistence.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
