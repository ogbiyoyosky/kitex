package com.kitex.kitex.restaurants.service;

import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.order.entity.PlacedOrder;
import com.kitex.kitex.restaurants.dto.CreateRestaurantDto;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;
import com.kitex.kitex.restaurants.dto.RestaurantResponseDto;
import com.kitex.kitex.restaurants.entity.Restaurant;

import java.util.List;

public interface IRestaurantService {
    Restaurant findRestaurantById(Integer restaurantId);
    List<RestaurantResponseDto> findRestaurants();
    PlacedOrder placeOrder(Integer restaurantId, PlaceOrderPayload payload, String userIdentifier);
    MenuItem findRestaurantSingleMenu(Integer restaurantId, Integer menuId);
    RestaurantResponseDto createRestaurant(CreateRestaurantDto payload, String userIdentifier);
}
