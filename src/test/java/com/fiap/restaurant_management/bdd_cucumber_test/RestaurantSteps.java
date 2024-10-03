package com.fiap.restaurant_management.bdd_cucumber_test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant_management.interfaces.dto.LocationCepDto;
import com.fiap.restaurant_management.interfaces.dto.LocationDto;
import com.fiap.restaurant_management.interfaces.dto.RestaurantDto;
import com.fiap.restaurant_management.interfaces.dto.RestaurantNameDto;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import com.fiap.restaurant_management.templateDto.RestaurantTemplateDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
public class RestaurantSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private RestaurantDto request;
    private MvcResult postResponse;

    @Before
    public void setUp() {
        restaurantRepository.deleteAll();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    }

    @Given("a restaurant with name {string}")
    public void aRestaurantWithName(String name) {
        request = RestaurantTemplateDto.restaurantTemplate();
        request = RestaurantDto.builder()
                .name(name)
                .location(request.location())
                .cuisineType(request.cuisineType())
                .totalTables(request.totalTables())
                .openingHours(request.openingHours())
                .closingTime(request.closingTime())
                .build();
    }

    @Given("a restaurant with cep {string}")
    public void aRestaurantWithCep(String cep) {
        request = RestaurantTemplateDto.restaurantTemplate();
        request = RestaurantDto.builder()
                .name(request.name())
                .location(new LocationDto(cep, request.location().number(), request.location().complement()))
                .cuisineType(request.cuisineType())
                .totalTables(request.totalTables())
                .openingHours(request.openingHours())
                .closingTime(request.closingTime())
                .build();
    }

    @Given("a restaurant with cuisine type {string}")
    public void aRestaurantWithCuisineType(String cuisineType) {
        request = RestaurantTemplateDto.restaurantTemplate();
        request = RestaurantDto.builder()
                .name(request.name())
                .location(request.location())
                .cuisineType(cuisineType)
                .totalTables(request.totalTables())
                .openingHours(request.openingHours())
                .closingTime(request.closingTime())
                .build();
    }

    @When("the restaurant is saved")
    public void theRestaurantIsSaved() throws Exception {
        postResponse = mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Then("the restaurant can be retrieved by name {string}")
    public void theRestaurantCanBeRetrievedByName(String name) throws Exception {
        RestaurantNameDto nameDto = new RestaurantNameDto(name);
        MvcResult getResponse = mockMvc.perform(get("/restaurant/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nameDto)))
                .andExpect(status().isOk())
                .andReturn();

        RestaurantDto foundRestaurant = objectMapper.readValue(getResponse.getResponse().getContentAsString(), RestaurantDto.class);
        assertEquals(request.name(), foundRestaurant.name());
    }

    @Then("the restaurant can be retrieved by cep {string}")
    public void theRestaurantCanBeRetrievedByCep(String cep) throws Exception {
        LocationCepDto locationDto = new LocationCepDto(cep);
        MvcResult getResponse = mockMvc.perform(get("/restaurant/cep")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(locationDto)))
                .andExpect(status().isOk())
                .andReturn();

        RestaurantDto foundRestaurant = objectMapper.readValue(getResponse.getResponse().getContentAsString(), RestaurantDto.class);
        assertEquals(request.location().cep(), foundRestaurant.location().cep());
    }

    @Then("the restaurant can be retrieved by cuisine type {string}")
    public void theRestaurantCanBeRetrievedByCuisineType(String cuisineType) throws Exception {
        MvcResult getResponse = mockMvc.perform(get("/restaurant/cuisine-type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("cuisine", cuisineType))
                .andExpect(status().isOk())
                .andReturn();

        List<RestaurantDto> foundRestaurants = objectMapper.readValue(getResponse.getResponse().getContentAsString(), new TypeReference<List<RestaurantDto>>() {});
        assertFalse(foundRestaurants.isEmpty());
        assertEquals(request.cuisineType(), foundRestaurants.get(0).cuisineType());
    }
}