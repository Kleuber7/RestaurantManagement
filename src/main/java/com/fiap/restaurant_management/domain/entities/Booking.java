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

    private Integer quantityOfPeople;

    private Status status;

    private Customer customerCode;

    private Restaurant RestaurantCode;

    public Booking(LocalDateTime reservationDate, Integer quantityOfPeople, Status status, Customer customerCode, Restaurant restaurantCode) {

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

        if(quantityOfPeople < 1 || restaurantCode.getCapacity() < quantityOfPeople) {
            throw new IllegalArgumentException("quantityOfPeople cannot be less than 1 or greater than the establishment's capacity");
        }

        if(status == null) {
            throw new IllegalArgumentException("status cannot be null");
        }

        this.reservationDate = reservationDate;
        this.quantityOfPeople = quantityOfPeople;
        this.status = status;
        this.customerCode = customerCode;
        RestaurantCode = restaurantCode;
    }

    public boolean isValidOpeningHours(LocalDateTime openingHours) {
        int hour = openingHours.getHour();
        return hour >= 6 && hour <= 23;
    }
}
