package com.java8streams.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java8streams.client.exception.ApiException;
import com.java8streams.client.exception.CoClientException;
import com.java8streams.client.response.CoClientResponse;
import com.java8streams.client.service.CoClientService;

@RestController
public class ClientController {
	
	@Autowired
	private CoClientService coClientService;

	/**
	 * Get details of all countries 
	 * @return
	 * @throws CoClientException
	 */
	@GetMapping("/all/details")
	public ResponseEntity<CoClientResponse> getAllDetails() throws CoClientException {
		CoClientResponse resp = new CoClientResponse();
		try {
			resp = coClientService.getAllDetails();
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
