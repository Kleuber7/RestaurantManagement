package com.fiap.restaurant_management.interfaces.dto;

import lombok.Builder;

@Builder
public record LocationDto(
        String cep,

        Integer number,

        String complement
) {
}
