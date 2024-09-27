package com.fiap.restaurant_management.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant_management.infra.dto.*;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import com.fiap.restaurant_management.infra.persistence.repository.BookingRepository;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import com.fiap.restaurant_management.infra.persistence.repository.ReviewRepository;
import com.fiap.restaurant_management.templateDto.BookingTemplateDto;
import com.fiap.restaurant_management.templateDto.CustomerTemplateDto;
import com.fiap.restaurant_management.templateDto.RestaurantTemplateDto;
import com.fiap.restaurant_management.templateDto.ReviewTemplateDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ReviewIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        reviewRepository.deleteAll();
        customerRepository.deleteAll();
        restaurantRepository.deleteAll();
        bookingRepository.deleteAll();
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
        CustomerEntity createdCustomer = objectMapper.readValue(postResponse, CustomerEntity.class);
        return createdCustomer;
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
        RestaurantEntity createdRestaurant = objectMapper.readValue(postResponse, RestaurantEntity.class);
        return createdRestaurant;
    }

    private void saveBooking(Long customerCode, Long restaurantCode) throws Exception {
        BookingDto request = BookingTemplateDto.bookingTemplate(customerCode, restaurantCode);
        MvcResult result = mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        BookingDto createdBooking = objectMapper.readValue(result.getResponse().getContentAsString(), BookingDto.class);
        updateBookingStatus(createdBooking.bookingCode(), 4);
    }

    private void updateBookingStatus(Long bookingCode, int status) throws Exception {
        BookingStatusDto request = BookingStatusDto.builder()
                .status(status)
                .build();
        mockMvc.perform(put("/booking/" + bookingCode)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


    @Test
    void createReview() throws Exception {
        CustomerEntity customer = saveCustomer();
        RestaurantEntity restaurant = saveRestaurant();
        saveBooking(customer.getCustomerCode(), restaurant.getRestaurantCode());

        ReviewDto request = ReviewTemplateDto.reviewTemplate(customer.getCustomerCode(), restaurant.getRestaurantCode());

        MvcResult result = mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        String postResponse = result.getResponse().getContentAsString();
        ReviewDto createdReview = objectMapper.readValue(postResponse, ReviewDto.class);

        assertEquals(request.rating(), createdReview.rating());
        assertEquals(request.comment(), createdReview.comment());
        assertEquals(request.restaurant(), createdReview.restaurant());
        assertEquals(request.customer(), createdReview.customer());
    }
}