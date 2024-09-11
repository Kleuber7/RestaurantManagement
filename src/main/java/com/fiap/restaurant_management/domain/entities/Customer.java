package com.fiap.restaurant_management.domain.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    private Long customerCode;

    private String name;

    private String email;

    private String phone;

    public Customer( String name, String email, String phone) {

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
