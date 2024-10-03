package com.fiap.restaurant_management.integrationTest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fiap.restaurant_management.infra.config.PageImplDeserializer;
import com.fiap.restaurant_management.interfaces.dto.BookingDto;
import com.fiap.restaurant_management.interfaces.dto.BookingStatusDto;
import com.fiap.restaurant_management.interfaces.dto.CustomerDto;
import com.fiap.restaurant_management.interfaces.dto.RestaurantDto;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import com.fiap.restaurant_management.infra.persistence.repository.BookingRepository;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import com.fiap.restaurant_management.templateDto.BookingTemplateDto;
import com.fiap.restaurant_management.templateDto.CustomerTemplateDto;
import com.fiap.restaurant_management.templateDto.RestaurantTemplateDto;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        objectMapper.addMixIn(PageImpl.class, PageImplMixin.class);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(PageImpl.class, new PageImplDeserializer());
        objectMapper.registerModule(module);
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
    @Severity(SeverityLevel.BLOCKER)
    void createBooking() throws Exception {

        RestAssured.filters(new AllureRestAssured());

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


    @Test
    @Severity(SeverityLevel.NORMAL)
    void getAllBookings() throws Exception {

        RestAssured.filters(new AllureRestAssured());

        CustomerEntity customer = saveCustomer();
        RestaurantEntity restaurant = saveRestaurant();

        BookingDto request = BookingTemplateDto.bookingTemplate(customer.getCustomerCode(), restaurant.getRestaurantCode());

        mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc.perform(get("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    void updateBooking() throws Exception {

        RestAssured.filters(new AllureRestAssured());

        CustomerEntity customer = saveCustomer();
        RestaurantEntity restaurant = saveRestaurant();

        BookingDto booking = saveBooking(customer, restaurant);

        BookingStatusDto updateRequest = new BookingStatusDto(2); // Assuming 2 is a valid status

        MvcResult result = mockMvc.perform(put("/booking/" + booking.bookingCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String putResponse = result.getResponse().getContentAsString();
        BookingDto updatedBooking = objectMapper.readValue(putResponse, BookingDto.class);

        assertEquals(updateRequest.status(), updatedBooking.status());
    }

    private BookingDto saveBooking(CustomerEntity customer, RestaurantEntity restaurant) throws Exception {
        BookingDto request = BookingTemplateDto.bookingTemplate(customer.getCustomerCode(), restaurant.getRestaurantCode());
        MvcResult result = mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
        String postResponse = result.getResponse().getContentAsString();
        return objectMapper.readValue(postResponse, BookingDto.class);
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PageImplMixin {
        @JsonCreator
        public PageImplMixin(@JsonProperty("content") List<?> content) {
        }
    }

}