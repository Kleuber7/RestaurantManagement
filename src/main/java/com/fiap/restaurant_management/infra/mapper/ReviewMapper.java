package com.fiap.restaurant_management.infra.mapper;

import com.fiap.restaurant_management.domain.entities.Review;
import com.fiap.restaurant_management.infra.persistence.entities.ReviewEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewMapper {

    private final RestaurantMapper restaurantMapper;
    private final CustomerMapper customerMapper;

    public ReviewEntity toEntity(Review review) {
        return new ReviewEntity(
                review.getRating(),
                review.getComment(),
                restaurantMapper.ToEntity(review.getRestaurant()),
                customerMapper.toEntity(review.getCustomer())
        );
    }

    public Review toEntityDomain(ReviewEntity reviewEntity) {
        return new Review(
                reviewEntity.getReviewCode(),
                reviewEntity.getRating(),
                reviewEntity.getComment(),
                restaurantMapper.FromEntityDomain(reviewEntity.getRestaurant()),
                customerMapper.toCustomer(reviewEntity.getCustomer())
        );
    }
}
