package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.aplication.exception.CustomerNotFoundException;
import com.fiap.restaurant_management.aplication.exception.RestaurantNotFoundException;
import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.infra.mapper.BookingMapper;
import com.fiap.restaurant_management.infra.persistence.entities.BookingEntity;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import com.fiap.restaurant_management.infra.persistence.repository.BookingRepository;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingRepositoryImpl  implements IBookingRepository {

    private final BookingRepository bookingRepository;
    private final RestaurantRepository restaurantRepository;
    private final CustomerRepository customerRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Booking createBooking(Booking booking, Long restaurantCode, Long customerCode) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantCode)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantCode));

        CustomerEntity customer = customerRepository
                .findById(customerCode).orElseThrow(() -> new CustomerNotFoundException(customerCode));

        BookingEntity bookingEntity = new BookingEntity(booking.getReservationDate(),
                booking.getQuantityOfPeople(),
                booking.getStatus(),
                customer,
                restaurant);

        bookingRepository.save(bookingEntity);

        return bookingMapper.toBookingEntityDomain(bookingEntity);
    }
}
