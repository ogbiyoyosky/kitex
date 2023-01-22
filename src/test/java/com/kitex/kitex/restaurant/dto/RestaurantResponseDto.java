package com.kitex.kitex.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kitex.kitex.auth.dto.DataDto;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDto {
        @JsonIgnore
        private String message;
        @JsonIgnore
        private Boolean status;
        @JsonIgnore
        private Integer statusCode;

        private RestaurantDataDto data;
}
