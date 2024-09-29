package com.fiap.restaurant_management.unit_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.aplication.usecases.restaurant.CreateRestaurant;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantByCep;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantBycuisineType;
import com.fiap.restaurant_management.domain.entities.Restaurant;
import com.fiap.restaurant_management.domain.valueobjects.Location;
import com.fiap.restaurant_management.infra.controller.RestaurantController;
import com.fiap.restaurant_management.infra.dto.RestaurantDto;
import com.fiap.restaurant_management.templateDto.RestaurantTemplateDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateRestaurant createRestaurant;

    @InjectMocks
    private RestaurantController restaurantController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Test
    public void testCreateRestaurant() throws Exception {
        RestaurantDto restaurantDto = RestaurantTemplateDto.restaurantTemplateController();

        Restaurant restaurant = new Restaurant(
                restaurantDto.restaurantCode(),
                restaurantDto.name(),
                new Location(
                        restaurantDto.location().cep(),
                        restaurantDto.location().number(),
                        restaurantDto.location().complement()
                ),
                restaurantDto.cuisineType(),
                restaurantDto.openingHours(),
                restaurantDto.closingTime(),
                restaurantDto.totalTables()
        );

        when(createRestaurant.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(restaurantDto)));
    }


}