package com.fiap.restaurant_management.interfaces.dto;

import lombok.Builder;

@Builder
public record ReviewDto(
        Double rating,
        String comment,
        Long restaurant,
        Long customer
) {
}
