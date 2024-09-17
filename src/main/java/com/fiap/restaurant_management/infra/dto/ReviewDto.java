package com.fiap.restaurant_management.infra.dto;

public record ReviewDto(
        Double rating,
        String comment,
        Long restaurant,
        Long customer
) {
}
