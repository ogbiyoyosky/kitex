package com.kitex.kitex.order.service;

import com.kitex.kitex.order.PlacedOrder;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;

public interface IOrderService {
    PlacedOrder createOrder(Integer restaurantId, PlaceOrderPayload orderPayload);

}
