package com.garage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.garage.data.TestMockApiData;
import com.garage.model.StatusConstants;
import com.garage.model.Warehouse;
import com.garage.service.WarehouseTrafficService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Mocking the webclient for unit testing: 1 demo test case
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class WarehouseDaoTest {

	@InjectMocks
	private WarehouseDao warehouseDao;
	private TestMockApiData testMockApiData;
	@Mock
	private WebClient webClient;
	@SuppressWarnings("rawtypes")
	@Mock
	private WebClient.RequestHeadersSpec requestHeadersSpec;
	@SuppressWarnings("rawtypes")
	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
	@Mock
	private WebClient.RequestBodySpec requestBodySpec;
	@Mock
	private WebClient.RequestBodyUriSpec requestBodyUriSpec;
	@Mock
	private WebClient.ResponseSpec responseSpec;
	@Mock
	private WarehouseTrafficService warehouseTrafficService;
	@Mock
	private StatusConstants statusConstants;
	private static final String EXTERNAL_API_PATH = "/b/5ebe673947a2266b1478d892";

	@BeforeEach
	void setup() {
		warehouseDao = new WarehouseDao(warehouseTrafficService,statusConstants,webClient );
		testMockApiData = new TestMockApiData();
	}

	@Test
	void callService() {
		doNothing().when(warehouseTrafficService).increaseCounter(Mockito.anyString());
		when(statusConstants.getTotal()).thenReturn("Total");
		when(statusConstants.getSuccess()).thenReturn("success");
		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(EXTERNAL_API_PATH)).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.onStatus(Mockito.any(), Mockito.any())).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(new ParameterizedTypeReference<List<Warehouse>>() {
		})).thenReturn(Mono.just(testMockApiData.getMockApiData()));
		Mono<List<Warehouse>> employeeMono = warehouseDao.getWarehouses();
		StepVerifier.create(employeeMono).assertNext(res -> {
			assertNotNull(res);
			assertEquals(1, res.size());
			assertEquals("id1", res.get(0).getId());
		}).verifyComplete();
	}
}
