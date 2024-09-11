package com.fiap.restaurant_management.domain.entities;

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

    private LocalDateTime openingHours = LocalDateTime.now();

    private Integer capacity;

    public Restaurant(String name, String location, String cuisineType, Integer capacity) {

        if( name == null || name.isEmpty() ){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if(!isValidOpeningHours(openingHours)){
            throw new IllegalArgumentException("It is not possible to make a reservation as it is outside opening hours");
        }

        // Fazer mais validações**************************************

        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.capacity = capacity;
    }

    private boolean isValidOpeningHours(LocalDateTime openingHours){
        int hour = openingHours.getHour();
        return hour >= 6 && hour < 23;
    }

}
