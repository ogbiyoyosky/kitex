package com.kitex.kitex.order.service;

import com.kitex.kitex.exception.BadRequestException;
import com.kitex.kitex.exception.NotFoundException;
import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.services.IMenuService;
import com.kitex.kitex.order.dto.OrderResponse;
import com.kitex.kitex.order.dto.TrackingOrderDto;
import com.kitex.kitex.order.dto.UpdateOrderDto;
import com.kitex.kitex.order.entity.PlacedOrder;
import com.kitex.kitex.order.entity.Status;
import com.kitex.kitex.order.dto.OrderItemDto;
import com.kitex.kitex.order.repository.IOrderRepository;
import com.kitex.kitex.restaurants.dto.PlaceOrderItem;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.restaurants.repository.IRestaurantRepository;
import com.kitex.kitex.restaurants.service.IRestaurantService;
import com.kitex.kitex.restaurants.service.RestaurantService;
import com.kitex.kitex.user.IUserService;
import com.kitex.kitex.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {
private final IOrderRepository orderRepository;

private final IRestaurantRepository restaurantRepository;
@Lazy
private final IUserService userService;
private final StatusService statusService;

@Lazy
private final IMenuService menuService;
private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
private static final String NUMBER = "0123456789";
private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
private static SecureRandom random = new SecureRandom();

    public PlacedOrder createOrder(Integer restaurantId, PlaceOrderPayload orderPayload, String userIdentifier) {

        Status status =  this.statusService.findStatusById(1);
        Restaurant restaurant = findRestaurantById(restaurantId);


        List<OrderItemDto> aggregatedOrder = this.validateAndAggregatePlacedOrderPayload(orderPayload, restaurantId);

        User user = userService.findByEmail(userIdentifier);
        PlacedOrder order = new PlacedOrder();
        order.setDeliveryAddress(orderPayload.getDeliveryAddress());
        order.setRestaurant(restaurant);
        order.setOrderReference(this.generateOrderRef(10));
        order.setPrice((float) 0);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(status);
        PlacedOrder savedOrder = orderRepository.save(order);

        System.out.println(aggregatedOrder);

        Float subtotal = 0.0f;
        for(OrderItemDto item : aggregatedOrder) {
            subtotal  = subtotal + item.getPrice();
            this.orderRepository.createOrderWithItems(savedOrder.getId(),item.getItemId(),item.getQuantity(),item.getItemPrice(),item.getPrice());
        }
        savedOrder.setPrice(subtotal);
        savedOrder.setSubTotal(subtotal);
        return this.updateOrder(savedOrder);
    }

    private List<OrderItemDto> validateAndAggregatePlacedOrderPayload(PlaceOrderPayload orderPayload, Integer restaurantId)  {
        List<OrderItemDto> orderList = new ArrayList<>();
        for (PlaceOrderItem item : orderPayload.getItems()) {
            MenuItem menu = this.menuService.getSingleRestaurantMenu(restaurantId,item.getItemId());
            if(menu == null) {
                throw new BadRequestException("item with id: " + item.getItemId() + " does not exist");
            }
            OrderItemDto orderTobeSubmitted = new OrderItemDto();
            orderTobeSubmitted.setItemId(item.getItemId());
            orderTobeSubmitted.setQuantity(item.getQty());
            orderTobeSubmitted.setItemPrice(menu.getPrice());
            orderTobeSubmitted.setPrice(item.getQty() * menu.getPrice());

            orderList.add(orderTobeSubmitted);
        }

        return orderList;
    }

    public PlacedOrder updateOrder(PlacedOrder order) {
        return orderRepository.save(order);
    }


    private String generateOrderRef(Integer len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < 7; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

    public List<OrderResponse> fetchRestaurantOrder(Integer restaurantId) {
        Restaurant restaurant = findRestaurantById(restaurantId);

         List<PlacedOrder> orders = orderRepository.findAllByRestaurant(restaurant);
            List<OrderResponse> responses = new ArrayList<>();

            // Iterate over the list of entities
            for (PlacedOrder order : orders) {
              OrderResponse response =  OrderResponse.builder().
                        orderItems(order.getPlacedOrderItems())
                        .orderTime(order.getOrderTime())
                        .estimatedDeliveryTime(order.getEstimatedDeliveryTime())
                        .orderReference(order.getOrderReference())
                        .id(order.getId())
                        .status(order.getStatus().getStatusName())
                        .price(order.getSubTotal())
                        .profile(order.getProfile())
                        .address(order.getDeliveryAddress())
                        .build();

                responses.add(response);

            }
            return responses;
    }

    @Override
    public PlacedOrder updateRestaurantOrder(Integer restaurantId, Integer orderId, UpdateOrderDto payload, String userIdentifier) {
        findRestaurantById(restaurantId);
        Status status;
        Optional<PlacedOrder> order = this.orderRepository.findById(orderId);
        if(order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        if(payload.getStatusId() != null) {
            status = this.statusService.findStatusById(payload.getStatusId());
            order.get().setStatus(status);
        }

        if(payload.getEstimatedDeliveryTime() != null) {
            order.get().setEstimatedDeliveryTime(LocalDateTime.parse(payload.getEstimatedDeliveryTime()));
        }

        return this.orderRepository.save(order.get());
    }

    @Override
    public PlacedOrder markOrderAsDelivered(Integer orderId) {
        Optional<PlacedOrder> order = orderRepository.findById(orderId);

        if(order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        Status status = this.statusService.findStatusByName("Delivered");
        order.get().setStatus(status);
        return orderRepository.save(order.get());
    }

    public Restaurant findRestaurantById (Integer restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }

        return restaurant.get();

    }

    @Override
    public PlacedOrder trackOrder(TrackingOrderDto payload) {
        Optional<PlacedOrder> order = this.orderRepository.findByOrderReference(payload.getReference());

        if(order.isEmpty()){
            throw new NotFoundException("tracking reference not found");
        }

        return order.get();
    }
}
