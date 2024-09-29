package com.fiap.restaurant_management.aplication.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantNotFoundExceptionTest {

    @Test
    public void testRestaurantNotFoundExceptionWithCode() {
        Long restaurantCode = 1L;
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            throw new RestaurantNotFoundException(restaurantCode);
        });
        assertEquals("Restaurant with code " + restaurantCode + " was not found", exception.getMessage());
    }

    @Test
    public void testRestaurantNotFoundExceptionWithName() {
        String restaurantName = "Test Restaurant";
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            throw new RestaurantNotFoundException(restaurantName);
        });
        assertEquals("Restaurant with name " + restaurantName + " was not found", exception.getMessage());
    }
}