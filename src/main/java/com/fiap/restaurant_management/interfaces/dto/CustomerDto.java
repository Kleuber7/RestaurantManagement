package com.fiap.restaurant_management.interfaces.dto;

import lombok.Builder;

@Builder
public record CustomerDto(
        Long customerCode,
        String name,
        String email,
        String phone
) {
}
