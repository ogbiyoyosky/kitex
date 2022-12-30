package com.kitex.kitex.menu.controller;


import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping(path = "/{restaurantId}")
    public List<MenuItem> getRestaurantMenu(@PathVariable("restaurantId") Integer restaurantId) {
        return  this.menuService.getRestaurantMenu(restaurantId);
    }

}
