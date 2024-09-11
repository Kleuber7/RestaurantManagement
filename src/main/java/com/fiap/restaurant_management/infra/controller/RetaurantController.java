package com.fiap.restaurant_management.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RetaurantController {

    @PostMapping
    public ResponseEntity createRestaurant(){
        return null;
        // Implementar o método
    }

}
