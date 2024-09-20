package com.fiap.restaurant_management.aplication.gateway;

import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.domain.enums.Status;

import java.util.List;

public interface IBookingRepository {

    Booking createBooking(Booking booking, Long restaurantCode, Long customerCode);

    List<Booking> getAllBookings(int page, int size);

    Booking updateBookingStatus(Long bookingCode, Integer status);

    Boolean existsBookingCode(Long bookingCode);
}
