package com.fiap.restaurant_management.aplication.usecases.review;

import com.fiap.restaurant_management.aplication.exception.CustomerNotFoundException;
import com.fiap.restaurant_management.aplication.exception.RestaurantNotFoundException;
import com.fiap.restaurant_management.aplication.exception.ReviewNotAllowedException;
import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.aplication.gateway.IReviewRepository;
import com.fiap.restaurant_management.domain.entities.Review;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class CreateReview {

    private final IReviewRepository reviewRepository;
    private final IRestaurantRepository restaurantRepository;
    private final ICustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateReview.class);


    public Review creatReview(Review review, Long restaurantCode, Long customerCode) {

        if(!restaurantRepository.existByRestaurantCode(restaurantCode)) {
            throw new RestaurantNotFoundException(restaurantCode);
        }

        if(!customerRepository.existsCustomerById(customerCode)) {
            throw new CustomerNotFoundException(restaurantCode);
        }

        if(!reviewRepository.customerCanMakeMvaluation(customerCode, restaurantCode)){
            throw new ReviewNotAllowedException(restaurantCode);
        }

        return reviewRepository.createReview(review, restaurantCode, customerCode);
    }


}
