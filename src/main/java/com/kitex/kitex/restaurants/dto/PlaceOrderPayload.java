package com.kitex.kitex.restaurants.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class PlaceOrderPayload {

    @NotEmpty()
    private  List<PlaceOrderItem> items;

    @NotBlank(message = "deliveryAddress cannot be blank")
    private  String deliveryAddress;
}

