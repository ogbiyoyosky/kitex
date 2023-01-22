package com.kitex.kitex.restaurants.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDto {
    private Integer id;
    private  String name;
    private String address;
}
