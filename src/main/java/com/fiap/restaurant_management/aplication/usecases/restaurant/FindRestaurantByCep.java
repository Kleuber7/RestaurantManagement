package com.fiap.restaurant_management.aplication.usecases.restaurant;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindRestaurantByCep {

    private final IRestaurantRepository restaurantRepository;

    public Restaurant findRestaurantByCep(String cep) {
        return restaurantRepository.findRestaurantByCep(cep);
    }
}
