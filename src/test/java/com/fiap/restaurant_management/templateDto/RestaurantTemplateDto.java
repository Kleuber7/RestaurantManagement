package com.fiap.restaurant_management.templateDto;

import com.fiap.restaurant_management.infra.dto.LocationDto;
import com.fiap.restaurant_management.infra.dto.RestaurantDto;

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

    public static RestaurantDto restaurantTemplate(String name) {
        return RestaurantDto.builder()
                .name(name)
                .location(new LocationDto("33455", 10, "Apt 1"))
                .cuisineType("brasileira")
                .totalTables(100)
                .openingHours(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(22, 0))
                .build();
    }


}
