package com.fiap.restaurant_management.infra.dto;

import java.time.LocalTime;

public record RestaurantDto(

        String name,

        LocationDto location,

        String cuisineType,

        Integer totalTables,

        LocalTime openingHours,

        LocalTime closingTime
) {
}
