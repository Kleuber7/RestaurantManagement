package com.fiap.restaurant_management.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant_management.infra.dto.CustomerDto;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.templateDto.CustomerTemplateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    }

    private CustomerDto saveCustomer() throws Exception {
        CustomerDto request = CustomerTemplateDto.customerTemplate();
        MvcResult result = mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
        String postResponse = result.getResponse().getContentAsString();
        return objectMapper.readValue(postResponse, CustomerDto.class);
    }

    @Test
    void createCustomer() throws Exception {
        CustomerDto createdCustomer = saveCustomer();
        CustomerDto request = CustomerTemplateDto.customerTemplate();
        assertEquals(request.name(), createdCustomer.name());
        assertEquals(request.email(), createdCustomer.email());
        assertEquals(request.phone(), createdCustomer.phone());
    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDto createdCustomer = saveCustomer();
        Long customerId = createdCustomer.customerCode();

        MvcResult getResult = mockMvc.perform(get("/customer/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        String getResponse = getResult.getResponse().getContentAsString();
        CustomerDto foundCustomer = objectMapper.readValue(getResponse, CustomerDto.class);
        Assertions.assertEquals(createdCustomer, foundCustomer);
    }

    @Test
    void getCustomerByIdNotFound() throws Exception {
        long nonExistentCustomerId = 999L;

        mockMvc.perform(get("/customer/" + nonExistentCustomerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found"));
    }
}

