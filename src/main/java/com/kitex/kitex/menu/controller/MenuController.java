package com.kitex.kitex.menu.controller;


import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.services.MenuService;
import com.kitex.kitex.order.dto.CreateMenuDto;
import com.kitex.kitex.util.Response;
import com.kitex.kitex.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/menus")
public class MenuController {
    private final MenuService menuService;

    @PreAuthorize("hasAnyAuthority('SCOPE_KITCHEN_ADMIN', 'SCOPE_CUSTOMER','DRIVER')")
    @GetMapping(path = "/{restaurantId}")
    public List<MenuItem> getRestaurantMenu(@PathVariable("restaurantId") Integer restaurantId) {
        return  this.menuService.getRestaurantMenu(restaurantId);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_KITCHEN_ADMIN')")
    @PostMapping(path = "/{restaurantId}/create")
    public ResponseEntity<ResponseDto> createRestaurantMenu(@PathVariable("restaurantId") Integer restaurantId, CreateMenuDto payload) {
        return Response.send("Successfully Created Menu", this.menuService.createRestaurantMenu(restaurantId, payload),201, true);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_KITCHEN_ADMIN')")
    @DeleteMapping(path = "/{menuId}")
    public ResponseEntity<ResponseDto> deleteRestaurantMenu(@PathVariable("menuId") Integer menuId) {
        this.menuService.deleteRestaurantMenu(menuId);
        return Response.send("Successfully deleted menu", null,202, true);
    }

}
