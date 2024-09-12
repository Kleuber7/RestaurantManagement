package com.fiap.restaurant_management.config;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.aplication.usecases.CreateRestaurant;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.infra.gateways.RestaurantRepositoryImpl;
import com.fiap.restaurant_management.infra.mapper.LocationMapper;
import com.fiap.restaurant_management.infra.mapper.RestaurantMapper;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantConfig {

    @Bean
    CreateRestaurant createRestaurant(IRestaurantRepository repository) {
        return new CreateRestaurant(repository);
    }

    @Bean
    RestaurantRepositoryImpl CustomRestaurantRepositoryImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        return new RestaurantRepositoryImpl(restaurantRepository, restaurantMapper);
    }

    @Bean
    RestaurantMapper restaurantMapper(LocationMapper locationMapper) {
        return new RestaurantMapper(locationMapper);
    }

    @Bean
    LocationMapper locationMapper() {
        return new LocationMapper();
    }
}
