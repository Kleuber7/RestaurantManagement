package com.fiap.restaurant_management.infra.controller;

import com.fiap.restaurant_management.aplication.usecases.review.CreateReview;
import com.fiap.restaurant_management.aplication.usecases.customer.FindCustomerById;
import com.fiap.restaurant_management.aplication.usecases.restaurant.FindRestaurantById;
import com.fiap.restaurant_management.domain.entities.Review;
import com.fiap.restaurant_management.infra.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final FindCustomerById findCustomerById;
    private final FindRestaurantById findRestaurantById;
    private final CreateReview createReview;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto dto) {
        var customer = findCustomerById.searchCustomerById(dto.customer());
        var restaurant = findRestaurantById.findRestaurantById(dto.restaurant());

        var review = createReview.creatReview(
                new Review(
                        dto.rating(),
                        dto.comment(),
                        restaurant,
                        customer),
                dto.restaurant(),
                dto.customer());

        var reviewDto = new ReviewDto(review.getRating(), review.getComment(), review.getRestaurant()
                .getRestaurantCode(), review.getCustomer().getCustomerCode());

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto);
    }
}
