package com.fiap.restaurant_management.templateDto;

import com.fiap.restaurant_management.infra.dto.BookingDto;

import java.time.LocalDateTime;

public class BookingTemplateDto {

    public static BookingDto bookingTemplate(Long customerCode, Long restaurantCode) {
        return BookingDto.builder()
                .reservationDate(LocalDateTime.of(2023, 10, 1, 16, 0))
                .numberOfTables(2)
                .customer(customerCode)
                .restaurant(restaurantCode)
                .status(2)
                .build();
    }


    public static BookingDto bookingTemplateCucumber(Long restaurantCode, String reservationDate, int numberOfTables, String status) {
        return BookingDto.builder()
                .reservationDate(LocalDateTime.parse(reservationDate))
                .numberOfTables(numberOfTables)
                .restaurant(restaurantCode)
                .status(Integer.valueOf(status))
                .build();
    }
}
