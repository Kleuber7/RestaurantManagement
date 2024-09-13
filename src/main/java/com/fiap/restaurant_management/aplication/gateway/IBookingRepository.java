package com.fiap.restaurant_management.aplication.gateway;

import com.fiap.restaurant_management.domain.entities.Booking;

public interface IBookingRepository {

    Booking createBooking(Booking booking);
}
