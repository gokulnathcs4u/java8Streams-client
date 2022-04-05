package com.java8streams.client.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java8streams.client.exception.ApiException;
import com.java8streams.client.exception.CoClientException;
import com.java8streams.client.response.CoClientResponse;
import com.java8streams.client.service.CoClientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/novelApi")
public class ClientNovelController {
	
	@Autowired
	private CoClientService coClientService;

	public ResponseEntity<CoClientResponse> allDetailsServiceFallback(Throwable e) {
		return new ResponseEntity<CoClientResponse>(new CoClientResponse(HttpStatus.SERVICE_UNAVAILABLE, null, 0, LocalDateTime.now()), HttpStatus.SERVICE_UNAVAILABLE);
		
	}
	/**
	 * Get details of all countries 
	 * @return
	 * @throws CoClientException
	 */
	@CircuitBreaker(name = "ALLDETAILSNOVEL", fallbackMethod = "allDetailsServiceFallback")
	@GetMapping("/all")
	public ResponseEntity<CoClientResponse> getAllDetails() throws CoClientException {
		CoClientResponse resp = new CoClientResponse();
		try {
			resp = coClientService.getAll();
		} catch (ApiException exception) {
			throw new CoClientException(exception.getErrorBo().getErrorCode(), exception.getErrorBo().getDescription(),
					exception);
		} catch (Exception exception) {
			throw new CoClientException(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
					exception);
		}
		return new ResponseEntity<CoClientResponse>(resp, resp.getStatus());
	}
}
