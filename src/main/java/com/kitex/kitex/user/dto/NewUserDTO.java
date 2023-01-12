package com.kitex.kitex.user.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
@Getter
@Builder
public class NewUserDTO {
    @NotBlank(message = "firstName cannot be blank")
    private final String firstName;


    @NotBlank(message = "lastName cannot be blank")
    private final String lastName;

    @NotNull(message = "Email must have a value")
    @Email(message = "Email is not in the correct format")
    private final String email;


    @NotBlank(message = "Password cannot be blank")
    private final String password;

    @NotBlank(message = "Phone cannot be blank")
    private final String phone;

    @NotBlank(message = "Address cannot be blank")
    private final String address;

}
