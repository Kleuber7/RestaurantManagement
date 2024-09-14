package com.fiap.restaurant_management.infra.persistence.entities;

import com.fiap.restaurant_management.domain.enums.Status;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "booking")
@Table(name = "BOOKING")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "bookingCode")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingCode;

    private LocalDateTime reservationDate;

    private Integer quantityOfPeople;

    private Status status;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerCode")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restauranteCode")
    private RestaurantEntity restaurant;
}
