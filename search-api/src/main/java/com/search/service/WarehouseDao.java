package com.garage.dao;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.garage.exception.GarageApiException;
import com.garage.model.StatusConstants;
import com.garage.model.Warehouse;
import com.garage.service.WarehouseTrafficService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Dao layer to interact with data source: External API
 *
 */
@Repository
@Slf4j
public class WarehouseDao implements IWarehouseDao {

	
	private WarehouseTrafficService warehouseTrafficService;
	public WarehouseDao(WarehouseTrafficService warehouseTrafficService, StatusConstants statusConstants,
			WebClient webClient) {
		this.warehouseTrafficService = warehouseTrafficService;
		this.statusConstants = statusConstants;
		this.webClient = webClient;
	}

	private StatusConstants statusConstants;

	private final WebClient webClient;
	private static final String EXTERNAL_API_PATH = "/b/5ebe673947a2266b1478d892";

//	public WarehouseDao(@Value("${external-service-baseurl}") String baseURL, WarehouseTrafficService warehouseTrafficService, StatusConstants statusConstants) {
//		this.webClient = WebClient.builder().baseUrl(baseURL).build();
//	}

	public Mono<List<Warehouse>> getWarehouses() {
		return webClient.get().uri(EXTERNAL_API_PATH).retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
					Mono<String> errMsg = clientResponse.bodyToMono(String.class);
					return errMsg.flatMap(msg -> {
						log.error(msg);
						warehouseTrafficService.increaseCounter(statusConstants.getBadRequest());
						warehouseTrafficService.increaseCounter(statusConstants.getTotal());
						return Mono.error(new GarageApiException(msg));
					});
				}).onStatus(HttpStatus::is5xxServerError, clientResponse -> {
					Mono<String> errMsg = clientResponse.bodyToMono(String.class);
					return errMsg.flatMap(msg -> {
						log.error(msg);
						warehouseTrafficService.increaseCounter(statusConstants.getSeerverError());
						warehouseTrafficService.increaseCounter(statusConstants.getTotal());
						return Mono.error(new GarageApiException(msg));
					});
				}).bodyToMono(new ParameterizedTypeReference<List<Warehouse>>() {
				})
				.doOnSuccess(onSuccess -> {
					warehouseTrafficService.increaseCounter(statusConstants.getTotal());
					warehouseTrafficService.increaseCounter(statusConstants.getSuccess());
				});

	}
}
