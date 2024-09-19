package com.fiap.restaurant_management.domain.entities;

import com.fiap.restaurant_management.domain.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Booking {

    private Long bookingCode;

    private LocalDateTime reservationDate;

    private Integer numberOfTables;

    private Status status = Status.PENDING;

    private Customer customer;

    private Restaurant restaurant;

    public Booking(LocalDateTime reservationDate, Integer numberOfTables, Customer customerCode, Restaurant restaurantCode) {

        if (customerCode == null) {
            throw new IllegalArgumentException("customerCode cannot be null");
        }

        if (restaurantCode == null) {
            throw new IllegalArgumentException("restaurantCode cannot be null");
        }

        if(!isValidOpeningHours(reservationDate)) {
            throw new IllegalArgumentException("It is not possible to make a reservation as it is outside opening hours");
        } else if (reservationDate == null) {
            throw new IllegalArgumentException("reservationDate cannot be null");
        }

        if(numberOfTables < 1 || restaurantCode.getTotalTables() < numberOfTables) {
            throw new IllegalArgumentException("number of tables cannot be less than 1 or greater than the establishment's capacity");
        }

        if(status == null) {
            throw new IllegalArgumentException("status cannot be null");
        }

        this.reservationDate = reservationDate;
        this.numberOfTables = numberOfTables;
        this.customer = customerCode;
        this.restaurant = restaurantCode;
    }


    public boolean isValidOpeningHours(LocalDateTime openingHours) {
        int hour = openingHours.getHour();
        return hour >= 6 && hour <= 23;
    }
}
