package com.kitex.kitex.menu.services;

import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.dto.CreateMenuDto;

import java.util.List;

public interface IMenuService {
     MenuItem getSingleRestaurantMenu(Integer restaurantId, Integer menuId);
     List<MenuItem> getRestaurantMenu(Integer restaurantId);
     MenuItem createRestaurantMenu(Integer restaurantId, CreateMenuDto payload);
     void deleteRestaurantMenu(Integer menuId);
}
