package com.fiap.restaurant_management.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private Long reviewCode;

    private Double rating;

    private String comment;

    private Integer restaurantCode;

    private Integer customerCode;

}
