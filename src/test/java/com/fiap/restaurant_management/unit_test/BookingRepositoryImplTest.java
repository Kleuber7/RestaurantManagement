package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.aplication.exception.BookingNotFoundException;
import com.fiap.restaurant_management.infra.mapper.BookingMapper;
import com.fiap.restaurant_management.infra.persistence.repository.BookingRepository;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BookingRepositoryImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingRepositoryImpl bookingRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowExceptionWhenStatusIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            bookingRepositoryImpl.updateBookingStatus(1L, null);
        });
    }

    @Test
    void shouldThrowExceptionWhenStatusIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            bookingRepositoryImpl.updateBookingStatus(1L, 5); // Assuming 5 is an invalid status
        });
    }

    @Test
    void shouldThrowExceptionWhenBookingNotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> {
            bookingRepositoryImpl.updateBookingStatus(1L, 2); // Assuming 2 is a valid status
        });
    }
}