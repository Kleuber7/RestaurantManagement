package com.fiap.restaurant_management.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "review")
@Table(name = "REVIEW")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "reviewCode")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewCode;

    private Double rating;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurat_code_PK")
    private RestaurantEntity restaurantCode;
    //falta criar relacionamento
    private Integer customerCode;
}
