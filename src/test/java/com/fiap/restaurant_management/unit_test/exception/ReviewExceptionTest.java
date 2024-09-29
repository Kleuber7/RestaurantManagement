package com.fiap.restaurant_management.unit_test.exception;

import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.domain.entities.Review;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReviewExceptionTest {

    @Test
    void shouldThrowExceptionWhenRatingIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(null, "Great food!", new Restaurant(), new Customer());
        });
    }

    @Test
    void shouldThrowExceptionWhenRatingIsNaN() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(Double.NaN, "Great food!", new Restaurant(), new Customer());
        });
    }

    @Test
    void shouldThrowExceptionWhenCommentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(4.5, null, new Restaurant(), new Customer());
        });
    }

    @Test
    void shouldThrowExceptionWhenCommentIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(4.5, "", new Restaurant(), new Customer());
        });
    }

    @Test
    void shouldThrowExceptionWhenRestaurantIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(4.5, "Great food!", null, new Customer());
        });
    }

    @Test
    void shouldThrowExceptionWhenCustomerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(4.5, "Great food!", new Restaurant(), null);
        });
    }
}
