package com.fiap.restaurant_management.infra.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerCode;
    private String name;
    private String email;
    private String phone;
    // falta implementar os relacionamentos


    public CustomerEntity(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
