package com.kitex.kitex.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
@Getter
public class LoginDTO
{

    @NotNull(message = "Email must have a value")
//    @Email(message = "Email is not in the correct format")
    private final String email;


    @NotBlank(message = "Password cannot be blank")
    private final String password;

}
