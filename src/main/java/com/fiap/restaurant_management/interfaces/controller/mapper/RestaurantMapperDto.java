package com.fiap.restaurant_management.interfaces.controller.mapper;

import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.interfaces.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantMapperDto {

    private final LocationMapperDto locationMapperDto;

    public RestaurantDto toRestaurantDto(Restaurant restaurant) {
        return new RestaurantDto(
                restaurant.getRestaurantCode(),
                restaurant.getName(),
                locationMapperDto.toLocationDto(restaurant.getLocation()),
                restaurant.getCuisineType(),
                restaurant.getTotalTables(),
                restaurant.getOpeningHours(),
                restaurant.getClosingTime()
        );
    }
}
