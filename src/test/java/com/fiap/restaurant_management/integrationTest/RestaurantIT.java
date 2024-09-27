package com.fiap.restaurant_management.integrationTest;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant_management.infra.dto.LocationCepDto;
import com.fiap.restaurant_management.infra.dto.RestaurantDto;
import com.fiap.restaurant_management.infra.dto.RestaurantNameDto;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import com.fiap.restaurant_management.templateDto.RestaurantTemplateDto;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository.deleteAll();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    }

    private RestaurantDto saveRestaurant() throws Exception {
        RestaurantDto request = RestaurantTemplateDto.restaurantTemplate();
        MvcResult result = mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
        String postResponse = result.getResponse().getContentAsString();
        return objectMapper.readValue(postResponse, RestaurantDto.class);
    }

    @Test
    void createRestaurant() throws Exception {
        RestaurantDto createdRestaurant = saveRestaurant();
        RestaurantDto request = RestaurantTemplateDto.restaurantTemplate();
        assertEquals(request.location(), createdRestaurant.location());
        assertEquals(request.name(), createdRestaurant.name());
        assertEquals(request.cuisineType(), createdRestaurant.cuisineType());
        assertEquals(request.totalTables(), createdRestaurant.totalTables());
        assertEquals(request.openingHours(), createdRestaurant.openingHours());
        assertEquals(request.closingTime(), createdRestaurant.closingTime());
    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    void createAndGetRestaurantByName() throws Exception {
        RestaurantDto createdRestaurant = saveRestaurant();
        String name = createdRestaurant.name();

        MvcResult getResultName = mockMvc.perform(get("/restaurant/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(new RestaurantNameDto(name))))
                .andExpect(status().isOk())
                .andReturn();

        String getResponseName = getResultName.getResponse().getContentAsString();
        RestaurantDto foundRestaurant = objectMapper.readValue(getResponseName, RestaurantDto.class);
        assertEquals("Restaurante comida brasileira", foundRestaurant.name());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    void createAndGetRestaurantByCep() throws Exception {
        RestaurantDto createdRestaurant = saveRestaurant();
        String cep = createdRestaurant.location().cep();

        MvcResult getResultCep = mockMvc.perform(get("/restaurant/cep")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(new LocationCepDto(cep))))
                .andExpect(status().isOk())
                .andReturn();

        String getResponseCep = getResultCep.getResponse().getContentAsString();
        RestaurantDto foundRestaurant = objectMapper.readValue(getResponseCep, RestaurantDto.class);
        assertEquals("33455", foundRestaurant.location().cep());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    void createAndGetRestaurantByCuisineType() throws Exception {
        RestaurantDto createdRestaurant = saveRestaurant();
        String cuisineType = createdRestaurant.cuisineType();

        MvcResult getResultCuisineType = mockMvc.perform(get("/restaurant/cuisine-type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("cuisine", cuisineType))
                .andExpect(status().isOk())
                .andReturn();

        String getResponseCuisineType = getResultCuisineType.getResponse().getContentAsString();
        List<RestaurantDto> foundRestaurants = objectMapper.readValue(getResponseCuisineType, new TypeReference<List<RestaurantDto>>() {});
        assertFalse(foundRestaurants.isEmpty());
        assertEquals("brasileira", foundRestaurants.get(0).cuisineType());
    }

}
