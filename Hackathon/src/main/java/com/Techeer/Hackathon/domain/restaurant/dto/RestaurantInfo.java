package com.Techeer.Hackathon.domain.restaurant.dto;

import com.Techeer.Hackathon.domain.restaurant.domain.RestaurantCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class RestaurantInfo {
    private String name;
    private RestaurantCategory category;

    private LocalDateTime createdDate;
}