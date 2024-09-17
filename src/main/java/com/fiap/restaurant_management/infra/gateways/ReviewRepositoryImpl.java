package com.fiap.restaurant_management.infra.gateways;

import com.fiap.restaurant_management.aplication.exception.CustomerNotFoundException;
import com.fiap.restaurant_management.aplication.exception.RestaurantNotFoundException;
import com.fiap.restaurant_management.aplication.gateway.IReviewRepository;
import com.fiap.restaurant_management.domain.entities.Review;
import com.fiap.restaurant_management.infra.mapper.ReviewMapper;
import com.fiap.restaurant_management.infra.persistence.entities.CustomerEntity;
import com.fiap.restaurant_management.infra.persistence.entities.RestaurantEntity;
import com.fiap.restaurant_management.infra.persistence.entities.ReviewEntity;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import com.fiap.restaurant_management.infra.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements IReviewRepository {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Review createReview(Review review, Long restaurantCode, Long customerCode) {
        CustomerEntity customerEntity = customerRepository
                .findById(customerCode).orElseThrow(() -> new CustomerNotFoundException(customerCode));

        RestaurantEntity restaurantEntity = restaurantRepository
                .findById(restaurantCode).orElseThrow(() -> new RestaurantNotFoundException(restaurantCode));

        var reviewEntity = new ReviewEntity(
                review.getRating(),
                review.getComment(),
                restaurantEntity,
                customerEntity
        );

        reviewRepository.save(reviewEntity);

        return reviewMapper.toEntityDomain(reviewEntity);
    }
}
