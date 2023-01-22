package com.kitex.kitex.restaurant.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDataDto {
    private  Integer id;
    private String name;
    private String address;
}
