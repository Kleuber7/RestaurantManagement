package com.fiap.restaurant_management.infra.controller;

import com.fiap.restaurant_management.aplication.usecases.restaurant.CreateRestaurant;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantByName;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.domain.valueobjects.Location;
import com.fiap.restaurant_management.infra.controller.mapper.RestaurantMapperDto;
import com.fiap.restaurant_management.infra.dto.LocationDto;
import com.fiap.restaurant_management.infra.dto.RestaurantDto;
import com.fiap.restaurant_management.infra.dto.RestaurantNameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final CreateRestaurant useCaseRestaurant;
    private final FindRestaurantByName useCaseFindRestaurantByName;
    private final RestaurantMapperDto restaurantMapperDto;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto dto) {
        Restaurant restaurantDomain = useCaseRestaurant.createRestaurant(new Restaurant(
                dto.name(),
                new Location(dto.location().cep(), dto.location().number(), dto.location().complement()),
                dto.cuisineType(),
                dto.openingHours(),
                dto.closingTime(),
                dto.totalTables()
        ));

        RestaurantDto restaurantDto = new RestaurantDto(
                restaurantDomain.getName(),
                new LocationDto(
                        restaurantDomain.getLocation().getCep(),
                        restaurantDomain.getLocation().getNumber(),
                        restaurantDomain.getLocation().getComplement()
                        ),
                restaurantDomain.getCuisineType(),
                restaurantDomain.getTotalTables(),
                restaurantDomain.getOpeningHours(),
                restaurantDomain.getClosingTime()
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantDto);
    }

    @GetMapping
    public ResponseEntity<RestaurantDto> findRestaurantByName(@RequestBody RestaurantNameDto name) {
        Restaurant restaurant = useCaseFindRestaurantByName.findRestaurantByName(name.name());

        RestaurantDto restaurantDto = restaurantMapperDto.toRestaurantDto(restaurant);

        return ResponseEntity.status(HttpStatus.OK).body(restaurantDto);
    }

}
