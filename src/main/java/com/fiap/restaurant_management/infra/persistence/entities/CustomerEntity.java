package com.fiap.restaurant_management.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerCode;

    private String name;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<BookingEntity> booking;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ReviewEntity> review;


    public CustomerEntity(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
