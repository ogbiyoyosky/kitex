package com.kitex.kitex.restaurants.controller;

import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.order.PlacedOrder;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.restaurants.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurantsWithMenus() {
       return this.restaurantService.findRestaurants();
    }

    @GetMapping(path = "/{restaurantId}/menus/{menuId}")
    public MenuItem getRestaurantSingleMenu(@PathVariable Integer restaurantId, @PathVariable Integer menuId) {
        return this.restaurantService.findRestaurantSingleMenu(restaurantId, menuId);
    }

    @PostMapping(path = "/{restaurantId}/place-order")
    public PlacedOrder placeOrder(@PathVariable Integer restaurantId, @RequestBody PlaceOrderPayload payload) {
        return this.restaurantService.placeOrder(restaurantId, payload);
    }
}
