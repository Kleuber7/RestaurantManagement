package com.fiap.restaurant_management.infra.dto;

import lombok.Builder;

@Builder
public record CustomerDto(
        Long customerCode,
        String name,
        String email,
        String phone
) {
}
