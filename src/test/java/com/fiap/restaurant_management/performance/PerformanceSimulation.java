package com.fiap.restaurant_management.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class PerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8080")
                    .header("Content-Type", "application/json");

    ActionBuilder registerCustomerRequests = http("request: Cadastrar cliente")
            .post("/customer")
            .body(StringBody("{\"name\": \"Benedita\", \"email\": \"benedita@gmail.com\", \"phone\": \"41956432123\"}"))
            .check(status().is(201))
            .check(jsonPath("$.customerCode").saveAs("customerCode"));

    ActionBuilder registerRestaurantRequest = http("request: Cadastrar restaurante")
            .post("/restaurant")
            .body(StringBody("{\"name\": \"Restaurant Mamamia\", \"location\": " +
                    "{\"cep\": \"98765147\", \"number\": 85, \"complement\": \"Ao lado do bar do chico\"}," +
                    " \"cuisineType\": \"Italiana\", \"totalTables\": 200, \"openingHours\": \"14:30:00\", " +
                    "\"closingTime\": \"23:00:00\"}"))
            .check(status().is(201))
            .check(jsonPath("$.restaurantCode").saveAs("restaurantCode"));

    ScenarioBuilder scenarioRegisterCustomerRequests= scenario("Cadastrar cliente")
            .exec(registerCustomerRequests);

    ScenarioBuilder scenarioRegisterRestaurantRequests= scenario("Cadastrar restaurante")
            .exec(registerRestaurantRequest);


    {
        setUp(
                scenarioRegisterCustomerRequests.injectOpen(
                        rampUsersPerSec(1)
                                .to(2)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(2)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(2)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                ),
                scenarioRegisterRestaurantRequests.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                )

        )
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(50)
                );
    }
}
