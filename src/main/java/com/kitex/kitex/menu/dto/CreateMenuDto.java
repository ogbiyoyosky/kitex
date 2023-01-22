package com.kitex.kitex.menu.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    private  Integer categoryId;

    @NotBlank
    private String recipe;


    private Float price;
}
