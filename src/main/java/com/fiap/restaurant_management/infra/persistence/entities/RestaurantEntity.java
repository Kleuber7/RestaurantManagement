package com.fiap.restaurant_management.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private LocalTime openingHours;

    private LocalTime closingTime;

    private Integer totalTables;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingEntity> booking;

    public RestaurantEntity(String name, LocationEntity location, String cuisineType, LocalTime openingHours, LocalTime closingTime, Integer capacity) {

        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
        this.closingTime = closingTime;
        this.totalTables = capacity;
    }
}
