package com.kitex.kitex.restaurants.service;

import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.order.PlacedOrder;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;
import com.kitex.kitex.restaurants.entity.Restaurant;

import java.util.List;

public interface IRestaurantService {
    Restaurant findRestaurantById(Integer restaurantId);
    List<Restaurant> findRestaurants();
    PlacedOrder placeOrder(Integer restaurantId, PlaceOrderPayload payload);
    MenuItem findRestaurantSingleMenu(Integer restaurantId, Integer menuId);
}
