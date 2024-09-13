package com.fiap.restaurant_management.aplication.usecases;

import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.domain.entities.Booking;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateBooking {

    private final IBookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.createBooking(booking);
    }
}
