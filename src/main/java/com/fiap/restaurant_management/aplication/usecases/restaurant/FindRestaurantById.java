package com.fiap.restaurant_management.aplication.usecases.restaurant;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindRestaurantById {

    private final IRestaurantRepository restaurantRepository;

    public Restaurant findRestaurantById(Long id) {
        return restaurantRepository.getRestaurantById(id);
    }

}
