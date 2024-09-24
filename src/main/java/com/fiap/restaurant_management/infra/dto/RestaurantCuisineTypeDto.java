package com.fiap.restaurant_management.infra.dto;

import lombok.Builder;

@Builder
public record RestaurantCuisineTypeDto(
        String CuisineType
) {
}
