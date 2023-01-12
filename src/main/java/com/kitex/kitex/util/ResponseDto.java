package com.kitex.kitex.util;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseDto {
    private String message;
    private  Boolean status;
    private Integer statusCode;
    private Object data;
}
