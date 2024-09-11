package com.fiap.restaurant_management.domain.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    private Long customerCode;
    private String name;
    private String email;
    private Integer phone;
}
