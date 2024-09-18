package com.fiap.restaurant_management.aplication.gateway;

import com.fiap.restaurant_management.domain.entities.Booking;

import java.util.List;

public interface IBookingRepository {

    Booking createBooking(Booking booking, Long restaurantCode, Long customerCode);

    List<Booking> getAllBookings(int page, int size);
}
