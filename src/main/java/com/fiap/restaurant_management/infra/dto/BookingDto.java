package com.fiap.restaurant_management.infra.dto;

import java.time.LocalDateTime;

public record BookingDto(

        LocalDateTime reservationDate,

        Integer quantityOfPeople,

        Long customer,

        Long restaurant

) {
}
