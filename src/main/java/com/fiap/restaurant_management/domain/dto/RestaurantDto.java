package com.fiap.restaurant_management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    private String name;
    private String location;
    private String cuisineType;
    private LocalDateTime openingHours;
    private Integer capacity;
}
