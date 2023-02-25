package com.Techeer.Hackathon.domain.restaurant.domain;

import com.Techeer.Hackathon.domain.restaurant.dto.RestaurantUpdateRequest;
import com.Techeer.Hackathon.domain.review.domain.Review;
import com.Techeer.Hackathon.global.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private RestaurantCategory category;

//    @OneToMany(mappedBy = "restaurant")
//    private List<Review> reviewList = new ArrayList<>();

    @Builder
    public Restaurant(String name, RestaurantCategory category) {
        this.name = name;
        this.category = category;
    }

    public void update(RestaurantUpdateRequest restaurantUpdateRequest){
        this.category = restaurantUpdateRequest.getCategory();
    }

    public void deleteRestaurant() {
        this.delete();
    }
}
