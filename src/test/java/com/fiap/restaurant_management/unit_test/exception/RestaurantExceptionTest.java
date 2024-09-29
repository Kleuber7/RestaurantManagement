package com.fiap.restaurant_management.unit_test.exception;

import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.domain.valueobjects.Location;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantExceptionTest {

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant(null, new Location("12345", 123, "Apt 1"), "Italian", LocalTime.of(10, 0), LocalTime.of(22, 0), 20);
        });
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("", new Location("12345", 123, "Apt 1"), "Italian", LocalTime.of(10, 0), LocalTime.of(22, 0), 20);
        });
    }

    @Test
    void shouldThrowExceptionWhenLocationIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("Test Restaurant", null, "Italian", LocalTime.of(10, 0), LocalTime.of(22, 0), 20);
        });
    }

    @Test
    void shouldThrowExceptionWhenCuisineTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("Test Restaurant", new Location("12345", 123, "Apt 1"), null, LocalTime.of(10, 0), LocalTime.of(22, 0), 20);
        });
    }

    @Test
    void shouldThrowExceptionWhenCuisineTypeIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("Test Restaurant", new Location("12345", 123, "Apt 1"), "", LocalTime.of(10, 0), LocalTime.of(22, 0), 20);
        });
    }

    @Test
    void shouldThrowExceptionWhenTablesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("Test Restaurant", new Location("12345", 123, "Apt 1"), "Italian", LocalTime.of(10, 0), LocalTime.of(22, 0), null);
        });
    }

    @Test
    void shouldThrowExceptionWhenTablesIsLessThan20() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("Test Restaurant", new Location("12345", 123, "Apt 1"), "Italian", LocalTime.of(10, 0), LocalTime.of(22, 0), 19);
        });
    }

    @Test
    void shouldThrowExceptionWhenOpeningHoursIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("Test Restaurant", new Location("12345", 123, "Apt 1"), "Italian", null, LocalTime.of(22, 0), 20);
        });
    }

    @Test
    void shouldThrowExceptionWhenClosingTimeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Restaurant("Test Restaurant", new Location("12345", 123, "Apt 1"), "Italian", LocalTime.of(10, 0), null, 20);
        });
    }
}