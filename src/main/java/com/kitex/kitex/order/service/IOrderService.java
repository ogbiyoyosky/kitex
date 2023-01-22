package com.kitex.kitex.order.service;

import com.kitex.kitex.order.dto.OrderResponse;
import com.kitex.kitex.order.dto.TrackOrderResponseDto;
import com.kitex.kitex.order.dto.TrackingOrderDto;
import com.kitex.kitex.order.dto.UpdateOrderDto;
import com.kitex.kitex.order.entity.PlacedOrder;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;

import java.util.List;

public interface IOrderService {
    PlacedOrder createOrder(Integer restaurantId, PlaceOrderPayload orderPayload, String userIdentifier);
    List<OrderResponse> fetchRestaurantOrder(Integer restaurantId);
    PlacedOrder updateRestaurantOrder(Integer restaurant, Integer orderId, UpdateOrderDto payload, String customerIdentifier);

    PlacedOrder markOrderAsDelivered(Integer orderId);

    TrackOrderResponseDto trackOrder(TrackingOrderDto payload);
}
