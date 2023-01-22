package com.kitex.kitex.order.dto;

import com.kitex.kitex.order.entity.PlacedOrderItems;
import com.kitex.kitex.order.entity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class TrackOrderResponseDto {
    private String orderRef;

    private String status;

    private LocalDateTime estimatedTimeForDelivery;

    private  Float totalPrice;

    private String address;

    private Integer orderId;

    private Set<PlacedOrderItems> orderItems;





}
