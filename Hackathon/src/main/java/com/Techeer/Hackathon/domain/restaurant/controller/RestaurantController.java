package com.Techeer.Hackathon.domain.restaurant.controller;


import com.Techeer.Hackathon.domain.restaurant.dto.RestaurantCreateRequest;
import com.Techeer.Hackathon.domain.restaurant.dto.RestaurantInfo;
import com.Techeer.Hackathon.domain.restaurant.dto.RestaurantUpdateRequest;
import com.Techeer.Hackathon.domain.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantInfo> createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        restaurantService.createRestaurant(restaurantCreateRequest);
        return ResponseEntity.ok(RestaurantInfo.builder()
                .createdDate(restaurantCreateRequest.getCreateDate())
                .category(restaurantCreateRequest.getCategory())
                .name(restaurantCreateRequest.getName())
                .build());
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantInfo> getRestaurant(@PathVariable Long id) {
        RestaurantInfo restaurantInfo = restaurantService.getRestaurantDetail(id);

        return ResponseEntity.ok(restaurantInfo);
    }

    @PutMapping("/restaurant")
    public ResponseEntity<RestaurantInfo> updateRestaurant(@RequestBody RestaurantUpdateRequest restaurantUpdateRequest) {
        RestaurantInfo restaurantInfo = restaurantService.updateRestaurant(restaurantUpdateRequest);
        return ResponseEntity.ok(restaurantInfo);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("삭제완료.");
    }
}
