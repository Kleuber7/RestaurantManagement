package com.fiap.restaurant_management.infra.dto;

import lombok.Builder;

@Builder
public record LocationDto(
        String cep,

        Integer number,

        String complement
) {
}
