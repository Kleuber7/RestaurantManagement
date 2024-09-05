package com.fiap.restaurant_management.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {

    private Long restaurantCode;
    private String name;
    private String location;
    private String cuisineType;
    private LocalDateTime openingHours;
    private Integer capacity;
}
