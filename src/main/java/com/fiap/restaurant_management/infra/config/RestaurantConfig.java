package com.fiap.restaurant_management.infra.config;

import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.aplication.usecases.restaurant.CreateRestaurant;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantByCep;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantByName;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantBycuisineType;
import com.fiap.restaurant_management.infra.controller.RestaurantController;
import com.fiap.restaurant_management.infra.controller.mapper.LocationMapperDto;
import com.fiap.restaurant_management.infra.controller.mapper.RestaurantMapperDto;
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

    @Bean
    public RestaurantController restaurantController(CreateRestaurant restaurant,
                                                     FindRestaurantByName findRestaurantByName,
                                                     RestaurantMapperDto restaurantMapperDto,
                                                     FindRestaurantByCep findRestaurantByCep,
                                                     FindRestaurantBycuisineType findRestaurantBycuisineType)  {
        return new RestaurantController(restaurant,
                findRestaurantByName,
                restaurantMapperDto,
                findRestaurantByCep,
                findRestaurantBycuisineType);
    }

    @Bean
    public FindRestaurantBycuisineType findRestaurantBycuisineType(IRestaurantRepository restaurantRepository) {
        return new FindRestaurantBycuisineType(restaurantRepository);
    }

    @Bean
    public FindRestaurantByCep findRestaurantByCep(IRestaurantRepository restaurantRepository) {
        return new FindRestaurantByCep(restaurantRepository);
    }

    @Bean
    public FindRestaurantByName findRestaurantByName(IRestaurantRepository repository) {
        return new FindRestaurantByName(repository);
    }

    @Bean
    public RestaurantMapperDto restaurantMapperDto(LocationMapperDto locationMapperDto) {
        return new RestaurantMapperDto(locationMapperDto);
    }

    @Bean
    public LocationMapperDto locationMapperDto() {
        return  new LocationMapperDto();
    }
}
