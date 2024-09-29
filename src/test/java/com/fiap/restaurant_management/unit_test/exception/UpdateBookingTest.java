package com.fiap.restaurant_management.unit_test;

import com.fiap.restaurant_management.aplication.exception.BookingNotFoundException;
import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.aplication.usecases.booking.UpdateBooking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UpdateBookingTest {

    private IBookingRepository bookingRepository;
    private UpdateBooking updateBooking;

    @BeforeEach
    void setUp() {
        bookingRepository = Mockito.mock(IBookingRepository.class);
        updateBooking = new UpdateBooking(bookingRepository);
    }

    @Test
    void shouldThrowBookingNotFoundException() {
        when(bookingRepository.existsBookingCode(anyLong())).thenReturn(false);

        assertThrows(BookingNotFoundException.class, () -> {
            updateBooking.updateBooking(1L, 1);
        });
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        when(bookingRepository.existsBookingCode(anyLong())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            updateBooking.updateBooking(1L, null);
        });
    }
}