package com.kitex.kitex.user.controller;

import com.kitex.kitex.factory.DTO_factory;
import com.kitex.kitex.user.UserService;
import com.kitex.kitex.user.dto.NewUserDTO;
import com.kitex.kitex.user.dto.UserDTO;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@Validated
public class UserController
{
      private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
      private final UserService userService;
      private final DTO_factory dtoFactory;

    @PostMapping(path = "api/auth/users")
    public UserDTO createAccount(@Valid @RequestBody NewUserDTO payload)
    {
        System.out.println("HELLLOOOOO" + payload.toString());
        return dtoFactory.create(userService.createUser(payload, "customer"));

    }



}
