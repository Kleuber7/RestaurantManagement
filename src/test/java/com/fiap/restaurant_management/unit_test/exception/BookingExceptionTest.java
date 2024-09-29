package com.fiap.restaurant_management.unit_test.exception;

import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingExceptionTest {

    @Test
    void shouldThrowExceptionWhenCustomerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Booking(1L, LocalDateTime.now(), 2, 1, null, new Restaurant());
        });
    }

    @Test
    void shouldThrowExceptionWhenRestaurantIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Booking(1L, LocalDateTime.now(), 2, 1, new Customer(), null);
        });
    }

    @Test
    void shouldThrowExceptionWhenReservationDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Booking(1L, null, 2, 1, new Customer(), new Restaurant());
        });
    }

    @Test
    void shouldThrowExceptionWhenReservationIsOutsideOpeningHours() {
        Restaurant restaurant = new Restaurant();
        LocalDateTime outsideOpeningHours = LocalDateTime.of(2023, 10, 10, 5, 0); // 5 AM is outside opening hours
        assertThrows(IllegalArgumentException.class, () -> {
            new Booking(1L, outsideOpeningHours, 2, 1, new Customer(), restaurant);
        });
    }

    @Test
    void shouldThrowExceptionWhenNumberOfTablesIsInvalid() {
        Restaurant restaurant = new Restaurant();
        restaurant.setTotalTables(10);
        assertThrows(IllegalArgumentException.class, () -> {
            new Booking(1L, LocalDateTime.now(), 0, 1, new Customer(), restaurant); // Less than 1 table
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Booking(1L, LocalDateTime.now(), 11, 1, new Customer(), restaurant); // More than restaurant capacity
        });
    }
}