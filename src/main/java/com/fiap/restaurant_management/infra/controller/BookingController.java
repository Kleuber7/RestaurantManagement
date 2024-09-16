package com.fiap.restaurant_management.infra.controller;

import com.fiap.restaurant_management.aplication.usecases.CreateBooking;
import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.infra.dto.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final CreateBooking createBookingUseCase;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto dto ){
        Booking bookingDomain = createBookingUseCase.createBooking(new Booking(
                dto.reservationDate(),
                dto.quantityOfPeople(),
                new Customer(),
                new Restaurant()
                )
                , dto.Restaurant(), dto.Restaurant());

        var bookingDto = new BookingDto(bookingDomain.getReservationDate(),
                bookingDomain.getQuantityOfPeople(),
                bookingDomain.getCustomer().getCustomerCode(),
                bookingDomain.getRestaurant().getRestaurantCode());

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }
}
