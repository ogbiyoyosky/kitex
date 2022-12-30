package com.kitex.kitex.restaurants.service;

import com.kitex.kitex.exception.NotFoundException;
import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.services.MenuService;
import com.kitex.kitex.order.PlacedOrder;
import com.kitex.kitex.order.service.IOrderService;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.restaurants.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Component
public class RestaurantService implements  IRestaurantService {
    private final IRestaurantRepository restaurantRepository;
    private final MenuService menuService;

    private  IOrderService orderService;

    @Autowired
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    public Restaurant findRestaurantById(Integer restaurantId) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(restaurantId);

        if(restaurant.isEmpty()) {
         throw new NotFoundException("Restaurant with id " + restaurantId + " not found");
        }

        return restaurant.get();
    }

    public List<Restaurant> findRestaurants() {
        return this.restaurantRepository.findAll();
    }

    public PlacedOrder placeOrder(Integer restaurantId, PlaceOrderPayload payload) {
        return orderService.createOrder(restaurantId, payload);
    }

    private Boolean validatePlaceOrderPayload(PlaceOrderPayload payload) {
        return true;
    }

    public MenuItem findRestaurantSingleMenu(Integer restaurantId, Integer menuId) {
        MenuItem menuItem = this.menuService.getSingleRestaurantMenu(restaurantId, menuId);
        if(menuItem == null) {
            throw new NotFoundException("Menu with id " + menuId + " not found");
        }
        return  menuItem;
    }
}
