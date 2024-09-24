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

    public BookingDto(LocalDateTime reservationDate, Integer numberOfTables, Integer status, Long customer, Long restaurant) {
        this(null, reservationDate, numberOfTables, status, customer, restaurant);
    }
}
