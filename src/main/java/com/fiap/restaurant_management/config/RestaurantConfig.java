package com.fiap.restaurant_management.config;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.aplication.usecases.CreateRestaurant;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantConfig {

    @Bean
    CreateRestaurant createRestaurant(IRestaurantRepository repository) {
        return new CreateRestaurant(repository);
    }


}
