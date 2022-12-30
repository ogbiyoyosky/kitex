package com.kitex.kitex.user.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
@Getter
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

}
