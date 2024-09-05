package com.fiap.restaurant_management.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {

    private Long reviewCode;
    private Double rating;
    private String comment;

}
