package com.kitex.kitex.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateMenuDto {
    @NotBlank
    private String itemName;

    @NotBlank
    private String description;

    @NotBlank
    private  String ingredients;

    @NotBlank
    private String recipe;

    @NotBlank
    @Min(1)
    private Float price;
}
