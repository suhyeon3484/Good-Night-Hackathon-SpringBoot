package com.Techeer.Hackathon.domain.review.service;

import com.Techeer.Hackathon.domain.restaurant.domain.Restaurant;
import com.Techeer.Hackathon.domain.restaurant.repository.RestaurantRepository;
import com.Techeer.Hackathon.domain.review.domain.Review;
import com.Techeer.Hackathon.domain.review.dto.ReviewCreateRequest;
import com.Techeer.Hackathon.domain.review.dto.ReviewInfo;
import com.Techeer.Hackathon.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;


    @Transactional
    public void createReview(ReviewCreateRequest reviewCreateRequest) {
        Restaurant foundRestaurant = restaurantRepository.findById(reviewCreateRequest.getRestaurantId())
                .orElseThrow(EntityNotFoundException::new);

        Review foundReview = mapReviewCreateRequestToReviewEntity(reviewCreateRequest, foundRestaurant);
        reviewRepository.save(foundReview);
    }

    public Review mapReviewCreateRequestToReviewEntity(ReviewCreateRequest reviewCreateRequest, Restaurant restaurant) {
        return Review.builder()
                .title(reviewCreateRequest.getTitle())
                .content(reviewCreateRequest.getContent())
                .restaurant(restaurant)
                .build();
    }
}

