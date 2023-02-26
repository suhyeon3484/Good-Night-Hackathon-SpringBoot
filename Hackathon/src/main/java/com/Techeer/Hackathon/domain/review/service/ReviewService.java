package com.Techeer.Hackathon.domain.review.service;

import com.Techeer.Hackathon.domain.restaurant.domain.Restaurant;
import com.Techeer.Hackathon.domain.restaurant.repository.RestaurantRepository;
import com.Techeer.Hackathon.domain.review.domain.Review;
import com.Techeer.Hackathon.domain.review.dto.ReviewCreateRequest;
import com.Techeer.Hackathon.domain.review.dto.ReviewInfo;
import com.Techeer.Hackathon.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public ReviewInfo getReviewDetail(Long id) {
        Review foundReview = reviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return mapReviewEntityToReviewInfo(foundReview);
    }

    @Transactional(readOnly = true)
    public List<ReviewInfo> getReviewListByPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return reviewRepository.findReviewWithPagination(pageRequest).stream()
                .map(this::mapReviewEntityToReviewInfo)
                .collect(Collectors.toList());
    }

    public Review mapReviewCreateRequestToReviewEntity(ReviewCreateRequest reviewCreateRequest, Restaurant restaurant) {
        return Review.builder()
                .title(reviewCreateRequest.getTitle())
                .content(reviewCreateRequest.getContent())
                .restaurant(restaurant)
                .build();
    }

    public ReviewInfo mapReviewEntityToReviewInfo(Review review) {
        return ReviewInfo.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .restaurantName(review.getRestaurant().getName())
                .build();
    }
}

