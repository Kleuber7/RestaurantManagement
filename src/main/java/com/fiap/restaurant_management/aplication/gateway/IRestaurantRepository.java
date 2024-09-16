package com.fiap.restaurant_management.aplication.gateway;

import com.fiap.restaurant_management.domain.entities.Restaurant;

public interface IRestaurantRepository {

    Restaurant createRestaurant(Restaurant restaurant);

    Boolean existByRestaurantCode(Long restaurantCode);

    Restaurant getRestaurantById(Long restaurantCode);
}
