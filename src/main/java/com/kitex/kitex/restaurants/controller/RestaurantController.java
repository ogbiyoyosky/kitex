package com.kitex.kitex.restaurants.controller;

import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.order.entity.PlacedOrder;
import com.kitex.kitex.restaurants.dto.CreateRestaurantDto;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.restaurants.service.RestaurantService;
import com.kitex.kitex.util.Response;
import com.kitex.kitex.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/api/restaurants")
public class RestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);
    private final RestaurantService restaurantService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN','SCOPE_ROLE_CUSTOMER')")
    @GetMapping
    public ResponseEntity<ResponseDto> getRestaurantsWithMenus(Principal principal) {
        LOG.info("authenticated user {} ", principal);
        return Response.send("Successfully fetched restaurants",this.restaurantService.findRestaurants(),200, true);
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN')")
    public ResponseEntity<ResponseDto> createRestaurant(Principal principal,@RequestBody CreateRestaurantDto payload) {
        this.restaurantService.createRestaurant(payload,principal.getName());
        return Response.send("Successfully created Restaurant",null,201, true);
    }
    @DeleteMapping(path="/{restaurantId}" )
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN')")
    public ResponseEntity<ResponseDto> deleteRestaurant(Principal principal,@PathVariable Integer restaurantId) {
        this.restaurantService.deleteRestaurant(principal.getName(),restaurantId);
        return Response.send("Successfully deleted Restaurant",null,202, true);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN', 'SCOPE_ROLE_CUSTOMER')")
    @GetMapping(path = "/{restaurantId}/menus/{menuId}")
    public MenuItem getRestaurantSingleMenu(@PathVariable Integer restaurantId, @PathVariable Integer menuId) {
        return this.restaurantService.findRestaurantSingleMenu(restaurantId, menuId);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_CUSTOMER')")
    @PostMapping(path = "/{restaurantId}/place-order")
    public ResponseEntity<ResponseDto> placeOrder(@PathVariable Integer restaurantId, @RequestBody PlaceOrderPayload payload, Authentication auth) {
        LOG.info("authenticated userId is {} ", auth.getName());
        this.restaurantService.placeOrder(restaurantId, payload, auth.getName());
        return Response.send("Successfully placed order",null,201, true);
    }
}
