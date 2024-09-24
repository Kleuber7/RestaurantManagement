package com.fiap.restaurant_management.aplication.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long restaurantCode) {
        super("Restaurant with code " + restaurantCode + " was not found");
    }

    public RestaurantNotFoundException(String name) {
        super("Restaurant with name " + name + " was not found");
    }
}
