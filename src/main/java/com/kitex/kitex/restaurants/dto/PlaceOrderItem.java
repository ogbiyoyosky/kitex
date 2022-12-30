package com.kitex.kitex.restaurants.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PlaceOrderItem {
    private final Integer itemId;

    @NotBlank(message = "qty cannot be blank")
    @Min(1)
    private final Integer qty;
}
