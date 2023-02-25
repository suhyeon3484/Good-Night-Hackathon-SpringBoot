package com.Techeer.Hackathon.domain.restaurant.dto;

import com.Techeer.Hackathon.domain.restaurant.domain.RestaurantCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantCreateRequest {
    private final LocalDateTime createDate;

    private final String name;

    private final RestaurantCategory category;
}