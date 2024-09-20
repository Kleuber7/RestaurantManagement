package com.fiap.restaurant_management.aplication.usecases.booking;

import com.fiap.restaurant_management.aplication.exception.BookingNotFoundException;
import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.domain.entities.Booking;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateBooking {

    private final IBookingRepository bookingRepository;

    public Booking updateBooking(Long bookingCode, Integer status) {

        if(!bookingRepository.existsBookingCode(bookingCode)) {
            throw new BookingNotFoundException(bookingCode);
        }

        if(status == null ) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        return bookingRepository.updateBookingStatus(bookingCode, status);
    }
}
