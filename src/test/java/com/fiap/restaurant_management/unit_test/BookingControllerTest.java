package com.fiap.restaurant_management.unit_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.restaurant_management.aplication.usecases.booking.CreateBooking;
import com.fiap.restaurant_management.aplication.usecases.customer.FindCustomerById;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantById;
import com.fiap.restaurant_management.domain.entities.Booking;
import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.domain.valueobjects.Location;
import com.fiap.restaurant_management.infra.controller.BookingController;
import com.fiap.restaurant_management.infra.controller.mapper.BookingMapperDto;
import com.fiap.restaurant_management.infra.dto.BookingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateBooking createBookingUseCase;

    @Mock
    private FindCustomerById findCustomerByIdUseCase;

    @Mock
    private FindRestaurantById findRestaurantByIdUseCase;

    @InjectMocks
    private BookingController bookingController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    public void testCreateBooking() throws Exception {
        BookingDto bookingDto = BookingDto.builder()
                .bookingCode(1L)
                .reservationDate(LocalDateTime.now())
                .numberOfTables(2)
                .status(1)
                .customer(1L)
                .restaurant(1L)
                .build();

        Customer customer = new Customer(1L, "Alfredo", "Alfredo.doe@example.com", "123456789");
        Location location = new Location("12345", 123, "Apt 1");
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", location, "Italian", LocalTime.of(10, 0), LocalTime.of(22, 0), 20);
        Booking booking = new Booking(bookingDto.reservationDate(), bookingDto.numberOfTables(), bookingDto.status(), customer, restaurant);

        when(findCustomerByIdUseCase.searchCustomerById(bookingDto.customer())).thenReturn(customer);
        when(findRestaurantByIdUseCase.findRestaurantById(bookingDto.restaurant())).thenReturn(restaurant);
        when(createBookingUseCase.createBooking(any(Booking.class), any(Long.class), any(Long.class))).thenReturn(booking);

        mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(BookingMapperDto.toBookingDto(booking))));
    }


}