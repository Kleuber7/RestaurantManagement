package com.fiap.restaurant_management.infra.mapper;

import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.infra.persistence.entities.BookingEntity;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingMapper {

    private final CustomerMapper customerEntityMapper;
    private final RestaurantMapper restaurantEntityMapper;

    public BookingEntity toBookingEntity(Booking booking) {

        return new BookingEntity(
                booking.getReservationDate(),
                booking.getNumberOfTables(),
                booking.getStatus(),
                customerEntityMapper.toEntity(booking.getCustomer()),
                restaurantEntityMapper.ToEntity(booking.getRestaurant())
                );
    }

    public Booking toBookingEntityDomain(BookingEntity bookingEntity) {
        return new Booking(
                bookingEntity.getReservationDate(),
                bookingEntity.getNumberOfTables(),
                customerEntityMapper.toCustomer(bookingEntity.getCustomer()),
                restaurantEntityMapper.FromEntityDomain(bookingEntity.getRestaurant())

        );
    }
}
