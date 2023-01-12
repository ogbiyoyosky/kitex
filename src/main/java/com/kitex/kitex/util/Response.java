package com.kitex.kitex.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Response {

	static public <T> ResponseEntity<ResponseDto> send(String message, T payload, Integer statusCode, Boolean status) {

		ResponseDto response = ResponseDto.builder()
				.statusCode(statusCode)
				.data(payload)
				.message(message)
				.status(status)
				.build();

		return ResponseEntity.status(statusCode).body(response);
	}

}
