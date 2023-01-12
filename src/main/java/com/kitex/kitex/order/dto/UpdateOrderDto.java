package com.kitex.kitex.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateOrderDto {

    private Integer statusId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String estimatedDeliveryTime;
}
