package com.kitex.kitex.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class UpdateOrderDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private String estimatedDeliveryTime;

    @Min(1)
    private  Integer statusId;
}
