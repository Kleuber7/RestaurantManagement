package com.fiap.restaurant_management.aplication.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long restaurantCode) {
        super("Restaurant with code " + restaurantCode + " was not found");
    }
}
