package com.fiap.restaurant_management.infra.dto;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record RestaurantDto(


        Long restaurantCode,

        String name,

        LocationDto location,

        String cuisineType,

        Integer totalTables,

        LocalTime openingHours,

        LocalTime closingTime
) {
}
