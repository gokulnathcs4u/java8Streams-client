package com.java8streams.client.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.java8streams.client.exception.ApiException;
import com.java8streams.client.exception.ErrorBo;
import com.java8streams.client.model.CoApi;
import com.java8streams.client.model.CovidAll;
import com.java8streams.client.response.CoClientResponse;

/**
 * 
 * @author GoCool
 *
 */
@Service
public class CoClientService {

	@Autowired
	private CoClientUrl coClientUrl;
	
	@Autowired
	private CoClientNovelUrl coClientNovelUrl;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 
	 * @return
	 * @throws ApiException
	 */
	public CoClientResponse getAllDetails() throws ApiException {
		ResponseEntity<CoClientResponse> responseEntity = restTemplate
				.getForEntity(coClientUrl.getHostName() + coClientUrl.getAlldetails(), CoClientResponse.class);

		if (Objects.nonNull(responseEntity)) {
			CoClientResponse resp = new CoClientResponse();
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				resp.setStatus(responseEntity.getStatusCode());
				List<CoApi> coapiList = (List<CoApi>) responseEntity.getBody().getValues();
				resp.setValues(coapiList);
				resp.setCount(coapiList.size());
			} else {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase()));
			}
			return resp;
		} else {
			throw new ApiException(
					new ErrorBo(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
		}
	}
	
	public CoClientResponse getAll() throws ApiException {
		ResponseEntity<CoClientResponse> responseEntity = restTemplate
				.getForEntity(coClientNovelUrl.getHostName() + coClientNovelUrl.getAll(), CoClientResponse.class);

		if (Objects.nonNull(responseEntity)) {
			CoClientResponse resp = new CoClientResponse();
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				resp.setStatus(responseEntity.getStatusCode());
				List<CovidAll> coapiList = (List<CovidAll>) responseEntity.getBody().getValues();
				resp.setValues(coapiList);
				resp.setCount(coapiList.size());
			} else {
				throw new ApiException(
						new ErrorBo(responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase()));
			}
			return resp;
		} else {
			throw new ApiException(
					new ErrorBo(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
		}
	}

}
