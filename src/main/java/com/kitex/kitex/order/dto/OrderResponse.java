package com.kitex.kitex.order.dto;

import com.kitex.kitex.profile.entity.Profile;
import com.kitex.kitex.order.entity.PlacedOrderItems;
import com.kitex.kitex.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Integer id;
    private LocalDateTime orderTime;
    private LocalDateTime estimatedDeliveryTime;
    private Float price;
    private String orderReference;
    private User user;
    private String address;
    private String status;
    private Set<PlacedOrderItems> orderItems;
}
