package com.kitex.kitex.menu.controller;


import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.services.MenuService;
import com.kitex.kitex.menu.dto.CreateMenuDto;
import com.kitex.kitex.util.Response;
import com.kitex.kitex.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/api/menus")
public class MenuController {
    private final MenuService menuService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN', 'SCOPE_ROLE_CUSTOMER','SCOPE_ROLE_DRIVER')")
    @GetMapping(path = "/{restaurantId}")
    public List<MenuItem> getRestaurantMenu(@PathVariable("restaurantId") Integer restaurantId) {
        return  this.menuService.getRestaurantMenu(restaurantId);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN')")
    @PostMapping(path = "restaurants/{restaurantId}/create")
    public ResponseEntity<ResponseDto> createRestaurantMenu(@PathVariable("restaurantId") Integer restaurantId,@Valid @RequestBody CreateMenuDto payload) {
        return Response.send("Successfully Created Menu", this.menuService.createRestaurantMenu(restaurantId, payload),201, true);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN')")
    @DeleteMapping(path = "/{menuId}")
    public ResponseEntity<ResponseDto> deleteRestaurantMenu(@PathVariable("menuId") Integer menuId) {
        this.menuService.deleteRestaurantMenu(menuId);
        return Response.send("Successfully deleted menu", null,202, true);
    }

//    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN')")
//    @PutMapping(path = "/{menuId}")
//    public ResponseEntity<ResponseDto> updateRestaurantMenu(@PathVariable("menuId") Integer menuId, @Valid @RequestBody CreateMenuDto payload) {
//        this.menuService.updateRestaurantMenu(menuId);
//        return Response.send("Successfully deleted menu", null,201, true);
//    }

}
