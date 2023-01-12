package com.kitex.kitex.user.controller;

import com.kitex.kitex.factory.DTO_factory;
import com.kitex.kitex.user.UserService;
import com.kitex.kitex.user.dto.NewUserDTO;



import com.kitex.kitex.util.Response;
import com.kitex.kitex.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/auth")
public class UserController
{
      private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
      private final UserService userService;
      private final DTO_factory dtoFactory;


    @PostMapping(path = "/users")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody NewUserDTO payload)
    {
        return Response.send("Successfully Created Account",dtoFactory.create(userService.createUser    (payload, "ROLE_CUSTOMER")),201, true);
    }

    @PostMapping(path = "/drivers")
    public ResponseEntity<ResponseDto>  createAccountForDrivers(@Valid @RequestBody NewUserDTO payload)
    {
     return   Response.send("Successfully Created Account",dtoFactory.create(userService.createUser(payload, "ROLE_DRIVER")),201, true);
    }

    @PostMapping(path = "/admins")
    public ResponseEntity<ResponseDto>  createAccountForAdmin(@Valid @RequestBody NewUserDTO payload)
    {
       return Response.send("Successfully Created Account",dtoFactory.create(userService.createUser(payload, "ROLE_KITCHEN_ADMIN")),201, true);
    }
}
