package com.kitex.kitex.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserResponseDto {
    private Integer id;
    private String firstName;
    private  String lastName;
    private String email;
}
