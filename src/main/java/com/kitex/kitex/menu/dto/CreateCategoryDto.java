package com.kitex.kitex.menu.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCategoryDto {
    @NotEmpty(message = "name is required")
    private String name;
}
