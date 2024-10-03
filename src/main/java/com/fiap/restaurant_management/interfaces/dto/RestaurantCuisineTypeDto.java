package com.fiap.restaurant_management.interfaces.dto;

import lombok.Builder;

@Builder
public record RestaurantCuisineTypeDto(
        String cuisineType
) {
}
