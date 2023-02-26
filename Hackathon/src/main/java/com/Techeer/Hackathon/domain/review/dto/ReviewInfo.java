package com.Techeer.Hackathon.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ReviewInfo {
    private long restaurantId;
    private String title;
    private String content;
}
