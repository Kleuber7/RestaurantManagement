package com.fiap.restaurant_management.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {

    private Long reservationCode;
    private Integer numberOfPeople;
    private LocalDateTime reservationDate;
}
