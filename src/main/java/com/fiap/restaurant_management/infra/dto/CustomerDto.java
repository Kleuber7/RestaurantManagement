package com.fiap.restaurant_management.infra.dto;

public record CustomerDto(
        String name,
        String email,
        String phone
) {
}
