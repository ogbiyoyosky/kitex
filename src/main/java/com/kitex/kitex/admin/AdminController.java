package com.kitex.kitex.admin;

import com.kitex.kitex.factory.DTO_factory;
import com.kitex.kitex.user.UserService;
import com.kitex.kitex.user.dto.NewUserDTO;
import com.kitex.kitex.user.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RequiredArgsConstructor
@RestController()
@RequestMapping(path = "api/auth/register")
@Validated
public class AdminController
{
    private final UserService userService;
    private final DTO_factory dtoFactory;
    @PostMapping(path = "/users")
    public UserDTO createAccount(@Valid @RequestBody NewUserDTO payload)
    {
        return dtoFactory.create(userService.createUser(payload, "kitchen_admin"));
    }

}

