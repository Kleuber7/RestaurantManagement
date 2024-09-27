package com.fiap.restaurant_management.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant_management.infra.dto.BookingDto;
import com.fiap.restaurant_management.infra.dto.CustomerDto;
import com.fiap.restaurant_management.infra.dto.RestaurantDto;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import com.fiap.restaurant_management.infra.persistence.repository.BookingRepository;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import com.fiap.restaurant_management.templateDto.BookingTemplateDto;
import com.fiap.restaurant_management.templateDto.CustomerTemplateDto;
import com.fiap.restaurant_management.templateDto.RestaurantTemplateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        bookingRepository.deleteAll();
        customerRepository.deleteAll();
        restaurantRepository.deleteAll();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    }

    private CustomerEntity saveCustomer() throws Exception {
        CustomerDto request = CustomerTemplateDto.customerTemplate();
        MvcResult result = mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
        String postResponse = result.getResponse().getContentAsString();
        return objectMapper.readValue(postResponse, CustomerEntity.class);
    }

    private RestaurantEntity saveRestaurant() throws Exception {
        RestaurantDto request = RestaurantTemplateDto.restaurantTemplate();
        MvcResult result = mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
        String postResponse = result.getResponse().getContentAsString();
        return objectMapper.readValue(postResponse, RestaurantEntity.class);
    }

    @Test
    void createBooking() throws Exception {
        CustomerEntity customer = saveCustomer();
        RestaurantEntity restaurant = saveRestaurant();

        BookingDto request = BookingTemplateDto.bookingTemplate(customer.getCustomerCode(), restaurant.getRestaurantCode());

        MvcResult result = mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        String postResponse = result.getResponse().getContentAsString();
        BookingDto createdBooking = objectMapper.readValue(postResponse, BookingDto.class);

        assertEquals(request.reservationDate(), createdBooking.reservationDate());
        assertEquals(request.numberOfTables(), createdBooking.numberOfTables());
        assertEquals(request.status(), createdBooking.status());
        assertEquals(request.customer(), createdBooking.customer());
        assertEquals(request.restaurant(), createdBooking.restaurant());
    }
}