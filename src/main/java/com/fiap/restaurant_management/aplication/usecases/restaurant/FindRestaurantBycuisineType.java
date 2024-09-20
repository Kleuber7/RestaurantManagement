package com.fiap.restaurant_management.aplication.usecases.restaurant;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindRestaurantBycuisineType {

    private final IRestaurantRepository restaurantRepository;

    public List<Restaurant> findRestaurantByCuisineType(String cuisineType) {
        return restaurantRepository.findRestaurantByCuisineType(cuisineType);
    }
}
