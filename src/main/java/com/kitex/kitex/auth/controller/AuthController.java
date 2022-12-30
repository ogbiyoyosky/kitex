package com.kitex.kitex.auth.controller;

import com.kitex.kitex.auth.TokenService;
import com.kitex.kitex.auth.dto.LoginDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Validated
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping(path = "api/auth/login")
    public String authenticateUser(@Valid @RequestBody LoginDTO payload) throws Exception {
        System.out.println("Hello");
        try {
           Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(payload.getEmail(),payload.getPassword())
            );

           System.out.println(authentication);
            return  tokenService.generateToken(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect credentials", e);
        }
    }
}
