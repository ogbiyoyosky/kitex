package com.kitex.kitex.restaurants.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
public class CreateRestaurantDto {
    @NotBlank(message = "provide the cityId")
    private final Integer cityId;

    @NotBlank(message = "provide an address")
    private final String address;

    @NotBlank(message = "provide a name")
    private final String name;

}
