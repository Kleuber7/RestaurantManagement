package com.fiap.restaurant_management.infra.mapper;

import com.fiap.restaurant_management.domain.valueobjects.Location;
import com.fiap.restaurant_management.infra.persistence.entities.LocationEntity;

public class LocationMapper {

    public LocationEntity toEntity(Location location) {
        return new LocationEntity(
                location.getCep(),
                location.getNumber(),
                location.getComplement()
        );
    }

    public Location toEntityDomain(LocationEntity locationEntity) {
        return new Location(
                locationEntity.getCep(),
                locationEntity.getNumber(),
                locationEntity.getComplement()
        );
    }
}
