package com.fiap.restaurant_management.infra.controller;

import com.fiap.restaurant_management.aplication.usecases.CreateBooking;
import com.fiap.restaurant_management.aplication.usecases.FindCustomerById;
import com.fiap.restaurant_management.aplication.usecases.FindRestaurantById;
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
    private final FindCustomerById findCustomerById;
    private final FindRestaurantById findRestaurantById;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto dto ){
        Customer customer = findCustomerById.searchCustomerById(dto.customer());
        Restaurant restaurant = findRestaurantById.findRestaurantById(dto.restaurant());

        Booking bookingDomain = createBookingUseCase.createBooking(new Booking(
                dto.reservationDate(),
                dto.quantityOfPeople(),
                customer,
                restaurant
                )
                , dto.restaurant(), dto.customer());


        var bookingDto = new BookingDto(bookingDomain.getReservationDate(),
                bookingDomain.getQuantityOfPeople(),
                customer.getCustomerCode(),
                restaurant.getRestaurantCode());

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }
}
