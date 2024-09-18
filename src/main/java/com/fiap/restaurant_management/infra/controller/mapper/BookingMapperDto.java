package com.fiap.restaurant_management.infra.controller.mapper;

import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.infra.dto.BookingDto;

public class BookingMapperDto {

    public static BookingDto toBookingDto(Booking booking) {
        return new BookingDto(
                booking.getReservationDate(),
                booking.getQuantityOfPeople(),
                booking.getCustomer().getCustomerCode(),
                booking.getRestaurant().getRestaurantCode()
        );
    }

    public static BookingDto toBookingDtoStatus(Booking booking) {
        return new BookingDto  (
                booking.getReservationDate(),
                booking.getQuantityOfPeople(),
                booking.getStatus(),
                booking.getCustomer().getCustomerCode(),
                booking.getRestaurant().getRestaurantCode()
        );
    }
}
