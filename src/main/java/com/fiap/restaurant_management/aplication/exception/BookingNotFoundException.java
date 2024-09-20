package com.fiap.restaurant_management.aplication.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long bookingCode) {
        super("Booking with code " + bookingCode + " was not found");
    }
}
