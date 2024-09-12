package com.fiap.restaurant_management.domain.entities;

import com.fiap.restaurant_management.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private Long bookingCode;

    private LocalDateTime reservationDate;

    private Integer quantityOfPeople;

    private Status status;

    private Integer customerCode;

    private Integer RestaurantCode;

}
