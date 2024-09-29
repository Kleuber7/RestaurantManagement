package com.fiap.restaurant_management.aplication.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingNotFoundExceptionTest {

    @Test
    public void testBookingNotFoundExceptionWithCode() {
        Long bookingCode = 1L;
        BookingNotFoundException exception = assertThrows(BookingNotFoundException.class, () -> {
            throw new BookingNotFoundException(bookingCode);
        });
        assertEquals("Booking with code " + bookingCode + " was not found", exception.getMessage());
    }
}