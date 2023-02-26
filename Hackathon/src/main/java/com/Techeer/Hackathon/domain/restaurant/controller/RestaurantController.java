package com.Techeer.Hackathon.domain.restaurant.controller;


import com.Techeer.Hackathon.domain.restaurant.domain.RestaurantCategory;
import com.Techeer.Hackathon.domain.restaurant.dto.RestaurantCreateRequest;
import com.Techeer.Hackathon.domain.restaurant.dto.RestaurantInfo;
import com.Techeer.Hackathon.domain.restaurant.dto.RestaurantUpdateRequest;
import com.Techeer.Hackathon.domain.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantInfo> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        restaurantService.createRestaurant(restaurantCreateRequest);
        return ResponseEntity.ok(RestaurantInfo.builder()
                .createdDate(restaurantCreateRequest.getCreateDate())
                .category(restaurantCreateRequest.getCategory())
                .name(restaurantCreateRequest.getName())
                .build());
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantInfo> getRestaurant(@PathVariable Long id) {
        RestaurantInfo restaurantInfo = restaurantService.getRestaurantDetail(id);

        return ResponseEntity.ok(restaurantInfo);
    }

    @GetMapping("/restaurants/lists")
    public ResponseEntity<List<RestaurantInfo>> getRestaurantListByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<RestaurantInfo> restaurantInfoList = restaurantService.getRestaurantListByPagination(page, size);
        return ResponseEntity.ok(restaurantInfoList);
    }

    @GetMapping("/restaurants/category-list")
    public ResponseEntity<List<RestaurantInfo>> getRestaurantsByCategoryWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam RestaurantCategory category
    ) {
        List<RestaurantInfo> restaurantInfoList = restaurantService.getRestaurantsByCategoryWithPagination(page, size, category);
        return ResponseEntity.ok(restaurantInfoList);
    }

    @PutMapping("/restaurants")
    public ResponseEntity<RestaurantInfo> updateRestaurant(@RequestBody RestaurantUpdateRequest restaurantUpdateRequest) {
        RestaurantInfo restaurantInfo = restaurantService.updateRestaurant(restaurantUpdateRequest);
        return ResponseEntity.ok(restaurantInfo);
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("삭제완료.");
    }
}
