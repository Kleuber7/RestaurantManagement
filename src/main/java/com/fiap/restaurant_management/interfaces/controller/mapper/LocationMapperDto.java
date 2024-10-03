package com.fiap.restaurant_management.interfaces.controller.mapper;

import com.fiap.restaurant_management.domain.valueobjects.Location;
import com.fiap.restaurant_management.interfaces.dto.LocationDto;

public class LocationMapperDto {

    public LocationDto toLocationDto(Location location) {
        return new LocationDto(
                location.getCep(),
                location.getNumber(),
                location.getComplement()
                );
    }
}
