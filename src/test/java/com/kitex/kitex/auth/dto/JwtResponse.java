package com.kitex.kitex.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    @JsonIgnore
    private String message;
    @JsonIgnore
    private Boolean status;
    @JsonIgnore
    private Integer statusCode;
    private  DataDto data;

}


