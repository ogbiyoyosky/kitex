package com.kitex.kitex.profile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NewProfileDto {
    @NotBlank(message = "Phone cannot be blank")
    private  String phone;

    @NotBlank(message = "Address cannot be blank")
    private  String address;
}
