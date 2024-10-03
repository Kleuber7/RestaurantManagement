package com.fiap.restaurant_management.interfaces.dto;

import lombok.Builder;

@Builder
public record LocationCepDto(
        String cep
) {
}
