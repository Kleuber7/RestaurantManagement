package com.fiap.restaurant_management.bdd_cucumber_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant_management.infra.dto.CustomerDto;
import com.fiap.restaurant_management.templateDto.CustomerTemplateDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CustomerDto request;
    private MvcResult postResponse;

    @Before
    public void cleanDatabase() {
        jdbcTemplate.execute("DELETE FROM customer");
    }

    @Given("a customer with name {string}, email {string}, and phone {string}")
    public void aCustomerWithNameEmailAndPhone(String name, String email, String phone) {
        request = CustomerTemplateDto.customerTemplate();
        request = CustomerDto.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .build();
    }

    @When("the customer is saved")
    public void theCustomerIsSaved() throws Exception {
        postResponse = mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Then("the customer can be retrieved by id")
    public void theCustomerCanBeRetrievedById() throws Exception {
        CustomerDto createdCustomer = objectMapper.readValue(postResponse.getResponse().getContentAsString(), CustomerDto.class);
        Long customerId = createdCustomer.customerCode();

        MvcResult getResult = mockMvc.perform(get("/customer/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        String getResponse = getResult.getResponse().getContentAsString();
        CustomerDto foundCustomer = objectMapper.readValue(getResponse, CustomerDto.class);
        assertEquals(createdCustomer, foundCustomer);
    }

    @Then("the customer retrieval by id should return not found for non-existent id")
    public void theCustomerRetrievalByIdShouldReturnNotFoundForNonExistentId() throws Exception {
        long nonExistentCustomerId = 999L;

        mockMvc.perform(get("/customer/" + nonExistentCustomerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found"));
    }
}