package com.kitex.kitex.restaurants.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
@Getter
public class PlaceOrderPayload {

    @NotEmpty()
    private final List<PlaceOrderItem> items;

    @NotBlank(message = "deliveryAddress cannot be blank")
    private final String deliveryAddress;
}

