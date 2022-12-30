package com.kitex.kitex.menu.services;

import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.repository.IMenuRepository;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.restaurants.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final IMenuRepository menuRepository;

    public List<MenuItem> getRestaurantMenu(Integer restaurantId) {
        //Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        List<MenuItem> menus = menuRepository.findAll();
        return menus;
    }

    public  MenuItem getSingleRestaurantMenu(Integer restaurantId, Integer menuId) {
        MenuItem menus = menuRepository.findAllByRestaurantAndMenu(restaurantId, menuId);
        return menus;
    }


}
