package com.fiap.restaurant_management.aplication.usecases.booking;

import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.domain.entities.Booking;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllBooking {

    private final IBookingRepository bookingRepository;

    public List<Booking> getAllBooking(int page, int size) {
        return  bookingRepository.getAllBookings(page, size);
    }
}
