package com.fiap.restaurant_management.infra.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookingDto(
        Long bookingCode,
        LocalDateTime reservationDate,
        Integer numberOfTables,
        Integer status,
        Long customer,
        Long restaurant
) {


}
