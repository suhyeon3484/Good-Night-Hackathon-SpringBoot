package com.Techeer.Hackathon.domain.review.controller;


import com.Techeer.Hackathon.domain.review.dto.ReviewCreateRequest;
import com.Techeer.Hackathon.domain.review.dto.ReviewCreateResponse;
import com.Techeer.Hackathon.domain.review.dto.ReviewInfo;
import com.Techeer.Hackathon.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/reviews/list")
    public ResponseEntity<List<ReviewInfo>> getReviewListByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReviewInfo> reviewInfoList = reviewService.getReviewListByPagination(page, size);
        return ResponseEntity.ok(reviewInfoList);
    }

    @GetMapping("/reviews/title")
    public ResponseEntity<List<ReviewInfo>> getReviewListWithTitleByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword
    ) {
        List<ReviewInfo> reviewInfoList =
                reviewService.getReviewListWithTitleByPagination(page, size, keyword);
        return ResponseEntity.ok(reviewInfoList);
    }

    @GetMapping("/reviews/content")
    public ResponseEntity<List<ReviewInfo>> getReviewListWithContentByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword
    ) {
        List<ReviewInfo> reviewInfoList =
                reviewService.getReviewListWithContentByPagination(page, size, keyword);
        return ResponseEntity.ok(reviewInfoList);
    }
}
