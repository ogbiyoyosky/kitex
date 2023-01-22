package com.kitex.kitex.restaurants.dto;

import com.kitex.kitex.menu.entity.MenuItem;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantWithMenusResponseDto {
    private String name;
    private String address;
    private Integer id;
    private List<MenuItem> menus;
}
