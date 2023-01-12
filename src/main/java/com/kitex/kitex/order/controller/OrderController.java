package com.kitex.kitex.order.controller;

import com.kitex.kitex.order.dto.OrderResponse;
import com.kitex.kitex.order.dto.TrackingOrderDto;
import com.kitex.kitex.order.dto.UpdateOrderDto;
import com.kitex.kitex.order.entity.PlacedOrder;
import com.kitex.kitex.order.service.OrderService;
import com.kitex.kitex.util.Response;
import com.kitex.kitex.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/orders")
@Validated
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasAnyRole('KITCHEN_ADMIN', 'CUSTOMER')")
    @GetMapping(path = "/restaurants/{restaurantId}")
    public List<OrderResponse> getRestaurantsWithMenus(@PathVariable Integer restaurantId) {
        return this.orderService.fetchRestaurantOrder(restaurantId);
    }

    @PreAuthorize("hasAnyRole('KITCHEN_ADMIN', 'CUSTOMER')")
    @PutMapping(path = "/{orderId}/restaurants/{restaurantId}")
    public ResponseEntity<ResponseDto> updateOrder(@PathVariable Integer restaurantId, @PathVariable Integer orderId, @Valid @RequestBody UpdateOrderDto payload, Authentication auth) {
        System.out.println(payload);
        return Response.send("Successfully updated order",this.orderService.updateRestaurantOrder(restaurantId,orderId, payload,auth.getName()),200,true);
    }

    @PreAuthorize("hasAnyRole('DRIVER')")
    @PutMapping(path = "/{orderId}/restaurants/{restaurantId}/delivered")
    public ResponseEntity<ResponseDto> markOrderAsDelivered(@PathVariable Integer restaurantId, @PathVariable Integer orderId, Authentication auth) {

        return Response.send("Successfully marked order as delivered",this.orderService.markOrderAsDelivered(orderId),200,true);
    }

    @PreAuthorize("hasAnyRole('KITCHEN_ADMIN', 'CUSTOMER')")
    @PostMapping(path = "/tracking")
    public ResponseEntity<ResponseDto> trackOrder(@PathVariable Integer restaurantId, @RequestBody TrackingOrderDto payload) {

        return Response.send("Successfully fetch order from reference",this.orderService.trackOrder(payload),200,true);
    }



}
