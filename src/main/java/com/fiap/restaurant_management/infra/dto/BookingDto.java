package com.fiap.restaurant_management.infra.dto;

import com.fiap.restaurant_management.domain.enums.Status;

import java.time.LocalDateTime;

public record BookingDto(
        Long bookingCode,
        LocalDateTime reservationDate,
        Integer numberOfTables,
        Status status,
        Long customer,
        Long restaurant
) {

    public BookingDto(LocalDateTime reservationDate, Integer numberOfTables, Status status, Long customer, Long restaurant) {
        this(null, reservationDate, numberOfTables, status, customer, restaurant);
    }
}
