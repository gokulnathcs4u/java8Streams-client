package com.java8streams.client.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.java8streams.client.response.ErrorResponse;

@ControllerAdvice
public class CoClientExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(CoClientException.class)
	protected ResponseEntity<ErrorResponse> handleExceptionInternal(CoClientException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus(ex.getStatus());
		response.setMessage(ex.getMessage());
		response.setTimestamp(ex.getTimestamp());
		response.setDebugMessage(ex.getDebugMessage());
		return new ResponseEntity<ErrorResponse>(response, ex.getStatus());
	}

}
