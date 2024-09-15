package com.fiap.restaurant_management.aplication.usecases;

import com.fiap.restaurant_management.aplication.exception.CustomerNotFoundException;
import com.fiap.restaurant_management.aplication.exception.RestaurantNotFoundException;
import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.domain.entities.Booking;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateBooking {

    private final IBookingRepository bookingRepository;
    private  final IRestaurantRepository restaurantRepository;
    private final ICustomerRepository customerRepository;

    public Booking createBooking(Booking booking, Long restaurantCode, Long customerCode) {

        if(!restaurantRepository.existByRestaurantCode(restaurantCode)){
            throw new RestaurantNotFoundException(restaurantCode);
        }

        if(customerRepository.existsCustomerById(customerCode)){
            throw new CustomerNotFoundException(customerCode);
        }

        return bookingRepository.createBooking(booking, restaurantCode, customerCode);
    }
}
