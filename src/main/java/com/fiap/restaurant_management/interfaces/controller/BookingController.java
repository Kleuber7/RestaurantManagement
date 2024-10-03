package com.fiap.restaurant_management.interfaces.controller;

import com.fiap.restaurant_management.aplication.usecases.booking.CreateBooking;
import com.fiap.restaurant_management.aplication.usecases.booking.GetAllBooking;
import com.fiap.restaurant_management.aplication.usecases.booking.UpdateBooking;
import com.fiap.restaurant_management.aplication.usecases.customer.FindCustomerById;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantById;
import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.interfaces.controller.mapper.BookingMapperDto;
import com.fiap.restaurant_management.interfaces.dto.BookingDto;
import com.fiap.restaurant_management.interfaces.dto.BookingStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final CreateBooking createBookingUseCase;
    private final FindCustomerById findCustomerByIdUseCase;
    private final FindRestaurantById findRestaurantByIdUseCase;
    private final GetAllBooking getAllBookingUseCase;
    private final UpdateBooking updateBookingUseCase;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto dto) {
        Customer customer = findCustomerByIdUseCase.searchCustomerById(dto.customer());
        Restaurant restaurant = findRestaurantByIdUseCase.findRestaurantById(dto.restaurant());

        Booking bookingDomain = createBookingUseCase.createBooking(new Booking(
                dto.reservationDate(),
                dto.numberOfTables(),
                dto.status(),
                customer,
                restaurant
        ), dto.restaurant(), dto.customer());

        var bookingDto = BookingMapperDto.toBookingDto(bookingDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }

    @GetMapping
    public ResponseEntity<Page<BookingDto>> getAllBookings(@PageableDefault(size = 10, sort = {"reservationDate"}) Pageable pageable) {

        List<Booking> bookingList = getAllBookingUseCase
                .getAllBooking(pageable.getPageNumber(), pageable.getPageSize());

        List<BookingDto> bookingDtoList = bookingList.stream().map(BookingMapperDto::toBookingDto)
                .collect(Collectors.toList());

        Page<BookingDto> bookingDtoPage = new PageImpl<>(bookingDtoList);

        return ResponseEntity.status(HttpStatus.OK).body(bookingDtoPage);
    }

    @PutMapping("/{bookingCode}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long bookingCode,
                                                    @RequestBody BookingStatusDto bookingStatusDto) {
        Booking booking = updateBookingUseCase.updateBooking(bookingCode, bookingStatusDto.status());
        BookingDto bookingDto = BookingMapperDto.toBookingDto(booking);
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto);
    }
}