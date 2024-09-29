package com.fiap.restaurant_management.infra.mapper;

import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.infra.persistence.entities.BookingEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingMapper {

    private final CustomerMapper customerEntityMapper;
    private final RestaurantMapper restaurantEntityMapper;


    public Booking toBookingEntityDomain(BookingEntity bookingEntity) {
        return new Booking(
                bookingEntity.getBookingCode(),
                bookingEntity.getReservationDate(),
                bookingEntity.getNumberOfTables(),
                bookingEntity.getStatus(),
                customerEntityMapper.toCustomer(bookingEntity.getCustomer()),
                restaurantEntityMapper.fromEntityDomain(bookingEntity.getRestaurant())

        );
    }
}
