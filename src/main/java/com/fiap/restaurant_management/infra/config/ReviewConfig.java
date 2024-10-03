package com.fiap.restaurant_management.infra.config;

import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.aplication.gateway.IReviewRepository;
import com.fiap.restaurant_management.aplication.usecases.review.CreateReview;
import com.fiap.restaurant_management.aplication.usecases.customer.FindCustomerById;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantById;
import com.fiap.restaurant_management.interfaces.controller.ReviewController;
import com.fiap.restaurant_management.infra.gateways.ReviewRepositoryImpl;
import com.fiap.restaurant_management.infra.mapper.CustomerMapper;
import com.fiap.restaurant_management.infra.mapper.RestaurantMapper;
import com.fiap.restaurant_management.infra.mapper.ReviewMapper;
import com.fiap.restaurant_management.infra.persistence.repository.BookingRepository;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import com.fiap.restaurant_management.infra.persistence.repository.ReviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewConfig {

    @Bean
    public ReviewController reviewController(FindCustomerById findCustomerById, FindRestaurantById findRestaurantById, CreateReview createReview) {
        return new ReviewController(findCustomerById, findRestaurantById, createReview);
    }

    @Bean
    public CreateReview createReview(IReviewRepository reviewRepository, IRestaurantRepository restaurantRepository, ICustomerRepository customerRepository) {
        return new CreateReview(reviewRepository, restaurantRepository, customerRepository);
    }

    @Bean
    public ReviewRepositoryImpl customReviewRepository(ReviewRepository restaurantRepository,
                                                       CustomerRepository customerRepository,
                                                       RestaurantRepository restaurantRepository2, ReviewMapper reviewMapper,
                                                       BookingRepository bookingRepository) {
        return  new ReviewRepositoryImpl(restaurantRepository,
                customerRepository, restaurantRepository2, reviewMapper, bookingRepository);
    }

    @Bean
    public ReviewMapper reviewMapper(RestaurantMapper restaurantMapper, CustomerMapper customerMapper) {
        return new ReviewMapper(restaurantMapper, customerMapper);
    }

}
