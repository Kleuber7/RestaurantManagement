package com.fiap.restaurant_management.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "restaurant")
@Table(name = "RESTAURANT")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode( of = "restaurantCode")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantCode;

    private String name;

    @Embedded
    private LocationEntity location;

    private String cuisineType;

    private LocalDateTime openingHours ;

    private LocalDateTime closingHours;

    private Integer capacity;

    @OneToMany(mappedBy = "restaurantCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();

    public RestaurantEntity(String name, LocationEntity location, String cuisineType, Integer capacity) {
        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.capacity = capacity;
    }
}
