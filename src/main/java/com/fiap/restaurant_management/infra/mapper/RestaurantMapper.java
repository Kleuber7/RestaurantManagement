package com.fiap.restaurant_management.infra.mapper;

import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantMapper {

    private final LocationMapper mapper;

    public RestaurantEntity ToEntity(Restaurant restaurant) {
        return new RestaurantEntity(
                restaurant.getName(),
                mapper.toEntity(restaurant.getLocation()),
                restaurant.getCuisineType(),
                restaurant.getCapacity()
        );
    }

    public Restaurant FromEntityDomain(RestaurantEntity restaurantEntity) {
        return new Restaurant(
                restaurantEntity.getName(),
                mapper.toEntityDomain(restaurantEntity.getLocation()),
                restaurantEntity.getCuisineType(),
                restaurantEntity.getCapacity()
        );
    }
}
