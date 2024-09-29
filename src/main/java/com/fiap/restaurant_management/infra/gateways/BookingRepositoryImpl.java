package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.aplication.exception.BookingNotFoundException;
import com.fiap.restaurant_management.aplication.exception.CustomerNotFoundException;
import com.fiap.restaurant_management.aplication.exception.RestaurantNotFoundException;
import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.domain.enums.Status;
import com.fiap.restaurant_management.infra.mapper.BookingMapper;
import com.fiap.restaurant_management.infra.persistence.entities.BookingEntity;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import com.fiap.restaurant_management.infra.persistence.repository.BookingRepository;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
                booking.getNumberOfTables(),
                booking.getStatus(),
                customer,
                restaurant);

        bookingRepository.save(bookingEntity);

        return bookingMapper.toBookingEntityDomain(bookingEntity);
    }

    @Override
    public List<Booking> getAllBookings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<BookingEntity> bookingList = bookingRepository.findAll(pageable).getContent();

        return bookingList.stream()
                .map( b  -> bookingMapper.toBookingEntityDomain(b))
                .collect(Collectors.toList());
    }


    @Override
    public Booking updateBookingStatus(Long bookingCode, Integer status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }

        BookingEntity bookingEntity = bookingRepository.findById(bookingCode)
                .orElseThrow(() -> new BookingNotFoundException(bookingCode));

        bookingEntity.setStatus(status);

        BookingEntity bookingSaved = bookingRepository.save(bookingEntity);

        return bookingMapper.toBookingEntityDomain(bookingSaved);
    }

    private boolean isValidStatus(Integer status) {
             return status >= 1 && status <= 4;
    }


    @Override
    public Boolean existsBookingCode(Long bookingCode) {
        return bookingRepository.existsById(bookingCode);
    }
}
