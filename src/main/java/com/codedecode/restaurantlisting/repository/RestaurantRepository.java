package com.codedecode.restaurantlisting.repository;

import com.codedecode.restaurantlisting.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
