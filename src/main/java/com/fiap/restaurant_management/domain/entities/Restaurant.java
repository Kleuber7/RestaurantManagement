package com.fiap.restaurant_management.domain.entities;

import com.fiap.restaurant_management.domain.valueobjects.Location;
import com.fiap.restaurant_management.domain.valueobjects.OpeningHours;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Restaurant {

    private Long restaurantCode;

    private String name;

    private Location location;

    private String cuisineType;

    private LocalDateTime openingHours = new OpeningHours(LocalDateTime.now()).getOpeningHours();

    private Integer capacity;

    private List<Review> reviews;

    public Restaurant(String name, Location location, String cuisineType, Integer capacity) {
        if( name == null || name.isEmpty() ){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if( location == null){
            throw new IllegalArgumentException("Location cannot be null");
        }

        if( cuisineType == null || cuisineType.isEmpty() ){
            throw new IllegalArgumentException("Cuisine type cannot be null or empty");
        }

        if( capacity == null || capacity < 20 ){
            throw new IllegalArgumentException("capacity cannot be less than 20");
        }

        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.capacity = capacity;
    }



}
