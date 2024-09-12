package com.fiap.restaurant_management.infra.dto;

public record LocationDto(
        String cep,

        Integer numero,

        String complemento
) {
}
