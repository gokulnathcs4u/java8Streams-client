package com.java8streams.client.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CoClientResponse {
	
	private HttpStatus status;
	private Object values;
	private int count;
	private LocalDateTime timestamp = LocalDateTime.now();

}
