package com.fiap.restaurant_management.aplication.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long custumerCode) {
        super("Customer with code " + custumerCode + " was not found");
    }
}
