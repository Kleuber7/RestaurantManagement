package com.fiap.restaurant_management.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Booking {

    private Long bookingCode;
    private LocalDateTime reservationDate;
    private Integer numberOfTables;
    private Integer status;
    private Customer customer;
    private Restaurant restaurant;

    public Booking(Long bookingCode, LocalDateTime reservationDate, Integer numberOfTables, Integer status, Customer customer, Restaurant restaurant) {
        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }
        if (restaurant == null) {
            throw new IllegalArgumentException("restaurant cannot be null");
        }
        if (reservationDate == null) {
            throw new IllegalArgumentException("reservationDate cannot be null");
        }
        if (!isValidOpeningHours(reservationDate)) {
            throw new IllegalArgumentException("It is not possible to make a reservation as it is outside opening hours");
        }
        if (numberOfTables < 1 || restaurant.getTotalTables() < numberOfTables) {
            throw new IllegalArgumentException("number of tables cannot be less than 1 or greater than the establishment's capacity");
        }

        this.bookingCode = bookingCode;
        this.reservationDate = reservationDate;
        this.numberOfTables = numberOfTables;
        this.status = status;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public Booking(LocalDateTime reservationDate, Integer numberOfTables, Integer status, Customer customer, Restaurant restaurant) {
        this(null, reservationDate, numberOfTables, status, customer, restaurant);
    }

    public boolean isValidOpeningHours(LocalDateTime openingHours) {
        int hour = openingHours.getHour();
        return hour >= 6 && hour <= 23;
    }
}