package com.fiap.restaurant_management.aplication.gateway;

import com.fiap.restaurant_management.domain.entities.Review;

public interface IReviewRepository {

    Review createReview(Review review, Long restaurantCode, Long customerCode);
}
