package com.fiap.restaurant_management.infra.controller;

import com.fiap.restaurant_management.aplication.usecases.CreateRestaurant;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.domain.valueobjects.Location;
import com.fiap.restaurant_management.infra.dto.LocationDto;
import com.fiap.restaurant_management.infra.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RetaurantController {

    private final CreateRestaurant useCaseRestaurant;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto dto) {
        Restaurant restaurantDomain = useCaseRestaurant.createRestaurant(new Restaurant(
                dto.name(),
                new Location(dto.location().cep(), dto.location().number(), dto.location().complement()),
                dto.cuisineType(),
                dto.openingHours(),
                dto.closingTime(),
                dto.capacity()
        ));

        RestaurantDto restaurantDto = new RestaurantDto(
                restaurantDomain.getName(),
                new LocationDto(
                        restaurantDomain.getLocation().getCep(),
                        restaurantDomain.getLocation().getNumber(),
                        restaurantDomain.getLocation().getComplement()
                        ),
                restaurantDomain.getCuisineType(),
                restaurantDomain.getCapacity(),
                restaurantDomain.getOpeningHours(),
                restaurantDomain.getOpeningHours()
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantDto);
    }

}
