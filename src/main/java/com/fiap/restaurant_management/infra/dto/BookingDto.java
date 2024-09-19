package com.fiap.restaurant_management.infra.dto;

import com.fiap.restaurant_management.domain.enums.Status;

import java.time.LocalDateTime;

public record BookingDto(

        LocalDateTime reservationDate,

        Integer numberOfTables,
        
        Status status,

        Long customer,

        Long restaurant

) {

}
