package com.kitex.kitex.order.service;

import com.kitex.kitex.customer.entity.Customer;
import com.kitex.kitex.customer.service.CustomerService;
import com.kitex.kitex.order.PlacedOrder;
import com.kitex.kitex.order.Status;
import com.kitex.kitex.order.repository.IOrderRepository;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.restaurants.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Lazy
@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {
private final IOrderRepository orderRepository;
private IRestaurantService restaurantService;
private final CustomerService customerService;

private final StatusService statusService;

@Autowired
public void setRestaurantService(IRestaurantService restaurantService) {
    this.restaurantService = restaurantService;
}

    public PlacedOrder createOrder(Integer restaurantId, PlaceOrderPayload orderPayload) {

        Status status =  this.statusService.findStatusById(1);
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        Customer customer = customerService.findCustomerById(1);
        PlacedOrder order = new PlacedOrder();
        order.setDeliveryAddress(orderPayload.getDeliveryAddress());
        order.setRestaurant(restaurant);
        order.setPrice((float) 100);
        order.setCustomer(customer);
        order.setOrderTime(LocalDateTime.now());
        order.setSubTotal((float) 100);
        order.setStatus(status);

        System.out.println(order);
        return orderRepository.save(order);
    }
}
