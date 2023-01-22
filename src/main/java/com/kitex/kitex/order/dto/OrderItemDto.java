package com.kitex.kitex.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderItemDto {
    private  Float price;
    private  Integer quantity;
    private  Integer itemId;
    private  Float itemPrice;
}
