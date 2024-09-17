package com.fiap.restaurant_management.domain.entities;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {

    private Long reviewCode;

    private Double rating;

    private String comment;

    private Restaurant restaurant;

    private Customer customer;

    public Review(Double rating, String comment, Restaurant restaurant, Customer customer) {

        if(rating == null || rating.isNaN()){
            throw new IllegalArgumentException("Review rating is invalid");
        }

        if(comment == null || comment.isEmpty()){
            throw new IllegalArgumentException("Review comment is invalid");
        }

        if(restaurant == null){
            throw new IllegalArgumentException("Restaurant is invalid or null");
        }

        if(customer ==  null ){
            throw new IllegalArgumentException("Customer is invalid or null");
        }

        this.rating = rating;
        this.comment = comment;
        this.restaurant = restaurant;
        this.customer = customer;
    }
}
