package com.Techeer.Hackathon.domain.review.controller;


import com.Techeer.Hackathon.domain.review.dto.ReviewCreateRequest;
import com.Techeer.Hackathon.domain.review.dto.ReviewCreateResponse;
import com.Techeer.Hackathon.domain.review.dto.ReviewInfo;
import com.Techeer.Hackathon.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<ReviewCreateResponse> createReview(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        reviewService.createReview(reviewCreateRequest);
        return ResponseEntity.ok(ReviewCreateResponse.builder()
                .content(reviewCreateRequest.getContent())
                .title(reviewCreateRequest.getTitle())
                .build());
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewInfo> getReviewDetail(@PathVariable Long id) {
        ReviewInfo reviewInfo = reviewService.getReviewDetail(id);

        return ResponseEntity.ok(reviewInfo);
    }


}
