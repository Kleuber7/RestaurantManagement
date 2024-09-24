package com.fiap.restaurant_management.infra.persistence.repository;

import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    @Query("SELECT r FROM restaurant r WHERE r.name LIKE :name%")
    List<RestaurantEntity> findByName(@Param("name") String name);

    @Query("SELECT r FROM restaurant r WHERE r.location.cep = :cep")
    List<RestaurantEntity> findByCep(@Param("cep") String cep);

    List<RestaurantEntity> findAllByCuisineType(String cuisineType);
}
