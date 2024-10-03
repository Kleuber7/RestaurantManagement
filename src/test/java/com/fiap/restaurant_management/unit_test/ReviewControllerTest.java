package com.fiap.restaurant_management.unit_test;

import com.fiap.restaurant_management.aplication.usecases.customer.FindCustomerById;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantById;
import com.fiap.restaurant_management.aplication.usecases.review.CreateReview;
import com.fiap.restaurant_management.domain.entities.Customer;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.domain.entities.Review;
import com.fiap.restaurant_management.domain.valueobjects.Location;
import com.fiap.restaurant_management.interfaces.controller.ReviewController;
import com.fiap.restaurant_management.interfaces.dto.ReviewDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FindCustomerById findCustomerById;

    @Mock
    private FindRestaurantById findRestaurantById;

    @Mock
    private CreateReview createReview;

    @InjectMocks
    private ReviewController reviewController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void testCreateReview() throws Exception {
        ReviewDto reviewDto = new ReviewDto(5.0, "Great food!", 1L, 1L);

        Customer customer = new Customer(1L, "amanda", "amanda.doe@example.com", "123456789");
        Location location = new Location("12345", 123, "Apt 1");
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", location, "Italian", LocalTime.of(10, 0), LocalTime.of(22, 0), 20);
        Review review = new Review(5.0, "Great food!", restaurant, customer);

        when(findCustomerById.searchCustomerById(reviewDto.customer())).thenReturn(customer);
        when(findRestaurantById.findRestaurantById(reviewDto.restaurant())).thenReturn(restaurant);
        when(createReview.creatReview(any(Review.class), any(Long.class), any(Long.class))).thenReturn(review);

        mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(reviewDto)));
    }
}