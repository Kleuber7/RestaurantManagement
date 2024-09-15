package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.infra.mapper.RestaurantMapper;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements IRestaurantRepository {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        RestaurantEntity domainEntity = mapper.ToEntity(restaurant);
        restaurantRepository.save(domainEntity);
        return mapper.FromEntityDomain(domainEntity);
    }

    @Override
    public Boolean existByRestaurantCode(Long restaurantCode) {
        return restaurantRepository.existsById(restaurantCode);
    }
}
