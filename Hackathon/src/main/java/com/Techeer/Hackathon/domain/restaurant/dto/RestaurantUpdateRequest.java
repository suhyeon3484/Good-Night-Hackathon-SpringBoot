package com.Techeer.Hackathon.domain.restaurant.dto;


import com.Techeer.Hackathon.domain.restaurant.domain.RestaurantCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantUpdateRequest {
    private final Long id;
    private final RestaurantCategory category;
}