package com.fiap.restaurant_management.infra.persistence.repository;

import com.fiap.restaurant_management.infra.persistence.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT COUNT(b) > 0 FROM booking b WHERE " +
            "b.customer.customerCode = :customerCode " +
            "AND b.restaurant.restaurantCode = :restaurantCode AND b.status = 4")
    Boolean hightCheck(@Param("customerCode") Long customerCode,
                       @Param("restaurantCode") Long restaurantCode);
}
