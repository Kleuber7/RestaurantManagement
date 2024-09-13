package com.fiap.restaurant_management.domain.entities;

import com.fiap.restaurant_management.domain.valueobjects.Location;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class Restaurant {

    private Long restaurantCode;

    private String name;

    private Location location;

    private String cuisineType;

    private LocalTime openingHours;

    private LocalTime closingTime;

    private Integer capacity;

    private List<Review> reviews;


    public Restaurant(String name, Location location, String cuisineType, LocalTime openingHours, LocalTime closingTime, Integer capacity) {

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

        if(openingHours == null){
            throw new IllegalArgumentException("Opening hours cannot be null");
        }

        if(closingTime == null){
            throw new IllegalArgumentException("closing Time cannot be null");
        }

        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
        this.closingTime = closingTime;
        this.capacity = capacity;
    }
}
