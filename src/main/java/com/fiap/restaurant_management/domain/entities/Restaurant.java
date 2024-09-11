package com.fiap.restaurant_management.domain.entities;

import com.fiap.restaurant_management.domain.valueobjects.OpeningHours;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Restaurant {

    private Long restaurantCode;

    private String name;

    private String location;

    private String cuisineType;

    private LocalDateTime openingHours = new OpeningHours(LocalDateTime.now()).getOpeningHours();

    private Integer capacity;

    public Restaurant(String name, String location, String cuisineType, Integer capacity) {

        if( name == null || name.isEmpty() ){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.capacity = capacity;
    }



}
