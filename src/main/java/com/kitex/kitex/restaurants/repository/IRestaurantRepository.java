package com.kitex.kitex.restaurants.repository;

import com.kitex.kitex.restaurants.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
