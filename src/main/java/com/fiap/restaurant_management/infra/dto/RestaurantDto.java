package com.fiap.restaurant_management.infra.dto;

import java.time.LocalDateTime;

public record RestaurantDto(

        String name,

        LocationDto location,

        String cuisineType,

        Integer capacity
) {
}
