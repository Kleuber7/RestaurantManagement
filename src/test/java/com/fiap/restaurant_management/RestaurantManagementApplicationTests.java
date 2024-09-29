package com.fiap.restaurant_management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantManagementApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void testCustomerController() {
        assertThat(applicationContext.containsBean("customerController")).isTrue();
    }

    @Test
    void testRestaurantController() {
        assertThat(applicationContext.containsBean("restaurantController")).isTrue();
    }

    @Test
    void testBookingController() {
        assertThat(applicationContext.containsBean("bookingController")).isTrue();
    }


}