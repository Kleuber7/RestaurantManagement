package com.fiap.restaurant_management.infra.dto;

import com.fiap.restaurant_management.domain.enums.Status;

import java.time.LocalDateTime;

public record BookingDto(

        LocalDateTime reservationDate,

        Integer quantityOfPeople,
        
        Status status,

        Long customer,

        Long restaurant

) {

    public BookingDto(LocalDateTime reservationDate, Integer quantityOfPeople, Long customerCode, Long restaurantCode) {
        this(reservationDate, quantityOfPeople, Status.PENDING, customerCode, restaurantCode);
    }
}
