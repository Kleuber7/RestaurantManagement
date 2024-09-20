package com.fiap.restaurant_management.infra.dto;

import com.fiap.restaurant_management.domain.enums.Status;

public record BookingStatusDto(
        Long BookingCode,
        Integer status
) {
}
