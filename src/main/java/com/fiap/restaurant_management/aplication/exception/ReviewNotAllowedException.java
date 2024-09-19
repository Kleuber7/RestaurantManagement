package com.fiap.restaurant_management.aplication.exception;

public class ReviewNotAllowedException extends RuntimeException{
    public ReviewNotAllowedException(Long customerCode) {
        super("customer " + customerCode + " cannot review the restaurant without the status of the FINISHED reservation");
    }
}
