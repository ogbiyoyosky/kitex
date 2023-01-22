package com.kitex.kitex.auth.controller;

import com.kitex.kitex.auth.TokenService;
import com.kitex.kitex.auth.dto.LoginDTO;
import com.kitex.kitex.exception.BadRequestException;
import com.kitex.kitex.util.Response;
import com.kitex.kitex.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    @PostMapping(path = "/login")
    public  ResponseEntity<ResponseDto> authenticateUser(@Valid @RequestBody LoginDTO payload) throws Exception {
        try {
           Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(payload.getEmail(),payload.getPassword())
           );
            Map<String, String> data = new HashMap<>();
            data.put("token",tokenService.generateToken(authentication));
            return Response.send("Successfully logged In",data,200, true);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Incorrect credentials");
        }
    }
}
