package com.fiap.restaurant_management.aplication.usecases.restaurant;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindRestaurantByName {

    private final IRestaurantRepository restaurantRepository;

    public Restaurant findRestaurantByName(String name){
        return restaurantRepository.findRestaurantByName(name);
    }

}
