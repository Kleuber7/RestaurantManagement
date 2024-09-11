package com.fiap.restaurant_management.domain.valueobjects;

import java.time.LocalDateTime;

public class OpeningHours {

    private  LocalDateTime openingHours ;

    public OpeningHours(LocalDateTime openingHours) {
        if (openingHours == null || !isValidOpeningHours(openingHours)) {
            throw new IllegalArgumentException("It is not possible to make a reservation as it is outside opening hours");
        }
        this.openingHours = openingHours;
    }

    private boolean isValidOpeningHours(LocalDateTime openingHours) {
        int hour = openingHours.getHour();
        return hour >= 6 && hour <= 23;
    }

    public LocalDateTime getOpeningHours() {
        return openingHours;
    }
}
