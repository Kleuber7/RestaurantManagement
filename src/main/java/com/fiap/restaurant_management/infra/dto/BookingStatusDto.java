package com.fiap.restaurant_management.infra.dto;

import lombok.Builder;

@Builder
public record BookingStatusDto(
        Integer status
) {
}
