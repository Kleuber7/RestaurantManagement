package com.fiap.restaurant_management.unit_test.exception;

import com.fiap.restaurant_management.aplication.exception.CustomerNotFoundException;
import com.fiap.restaurant_management.aplication.exception.RestaurantNotFoundException;
import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.aplication.usecases.booking.CreateBooking;
import com.fiap.restaurant_management.domain.entities.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CreateBookingExcpetionTest {


    private IBookingRepository bookingRepository;
    private IRestaurantRepository restaurantRepository;
    private ICustomerRepository customerRepository;
    private CreateBooking createBooking;


    @BeforeEach
    void setUp() {
        bookingRepository = Mockito.mock(IBookingRepository.class);
        restaurantRepository = Mockito.mock(IRestaurantRepository.class);
        customerRepository = Mockito.mock(ICustomerRepository.class);
        createBooking = new CreateBooking(bookingRepository, restaurantRepository, customerRepository);
    }

    @Test
    void shouldThrowRestaurantNotFoundException() {
        when(restaurantRepository.existByRestaurantCode(anyLong())).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> {
            createBooking.createBooking(new Booking(), 1L, 1L);
        });
    }

    @Test
    void shouldThrowCustomerNotFoundException() {
        when(restaurantRepository.existByRestaurantCode(anyLong())).thenReturn(true);
        when(customerRepository.existsCustomerById(anyLong())).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            createBooking.createBooking(new Booking(), 1L, 1L);
        });
    }
}
