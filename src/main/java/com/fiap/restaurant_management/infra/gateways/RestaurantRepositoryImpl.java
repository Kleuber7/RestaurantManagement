package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.aplication.exception.RestaurantNotFoundException;
import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.infra.mapper.RestaurantMapper;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements IRestaurantRepository {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        RestaurantEntity domainEntity = mapper.toEntity(restaurant);
        restaurantRepository.save(domainEntity);
        return mapper.fromEntityDomain(domainEntity);
    }

    @Override
    public Boolean existByRestaurantCode(Long restaurantCode) {
        return restaurantRepository.existsById(restaurantCode);
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantCode) {
        var restaurantEntity =  restaurantRepository.findById(restaurantCode)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantCode));

        return mapper.fromEntityDomain(restaurantEntity);
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByName(name)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException(name));
        return mapper.fromEntityDomain(restaurantEntity);
    }

    @Override
    public Restaurant findRestaurantByCep(String cep) {
        RestaurantEntity restaurant = restaurantRepository.findByCep(cep)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException(cep));
        return mapper.fromEntityDomain(restaurant);
    }

    @Override
    public List<Restaurant> findRestaurantByCuisineType(String cuisineType) {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAllByCuisineType(cuisineType);

        return restaurantEntityList
                .stream().
                map(mapper::fromEntityDomain)
                .collect(Collectors.toList());
    }
}
