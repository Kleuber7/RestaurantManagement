package com.fiap.restaurant_management.templateDto;

import com.fiap.restaurant_management.infra.dto.ReviewDto;

public class ReviewTemplateDto {

    public static ReviewDto reviewTemplate(Long customerCode, Long restaurantCode) {
        return ReviewDto.builder()
                .rating(5.0)
                .comment("Excelente restaurante")
                .customer(customerCode)
                .restaurant(restaurantCode)
                .build();
    }

    public static ReviewDto reviewTemplateCucumber( Long restaurantCode, double rating, String comment) {
        return ReviewDto.builder()
                .rating(rating)
                .comment(comment)
                .customer(1L)
                .restaurant(restaurantCode)
                .build();
    }
}
