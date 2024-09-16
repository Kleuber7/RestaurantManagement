package com.fiap.restaurant_management.domain.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {

    private Long customerCode;

    private String name;

    private String email;

    private String phone;

    private List<Review> reviews = new ArrayList<>();

    public Customer( Long customerCode, String name, String email, String phone) {

        if( name == null || name.isBlank() ) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if( email == null || email.isBlank() ) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.customerCode = customerCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Customer(  String name, String email, String phone) {

        if( name == null || name.isBlank() ) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if( email == null || email.isBlank() ) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
