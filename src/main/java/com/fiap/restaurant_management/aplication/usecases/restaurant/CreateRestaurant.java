package com.fiap.restaurant_management.aplication.usecases.restaurant;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRestaurant {
    private final IRestaurantRepository repository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        return repository.createRestaurant(restaurant);
    }
}
