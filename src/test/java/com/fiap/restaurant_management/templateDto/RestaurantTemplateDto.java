package com.fiap.restaurant_management.templateDto;

import com.fiap.restaurant_management.interfaces.dto.LocationDto;
import com.fiap.restaurant_management.interfaces.dto.RestaurantDto;

import java.time.LocalTime;

public class RestaurantTemplateDto {


    public static RestaurantDto restaurantTemplate() {
        return RestaurantDto.builder()
                .name("Restaurante comida brasileira")
                .location(new LocationDto("33455", 10, "Apt 1"))
                .cuisineType("brasileira")
                .totalTables(100)
                .openingHours(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(22, 0))
                .build();
    }

    public static RestaurantDto restaurantTemplateController() {
        return RestaurantDto.builder()
                .restaurantCode(1L)
                .name("Restaurante comida brasileira")
                .location(new LocationDto("33455", 10, "Apt 1"))
                .cuisineType("brasileira")
                .totalTables(100)
                .openingHours(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(22, 0))
                .build();
    }


}
