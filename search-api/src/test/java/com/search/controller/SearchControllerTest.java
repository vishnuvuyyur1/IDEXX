package com.search.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.search.model.ApiResponse;
import com.search.service.SearchService;

import reactor.core.publisher.Mono;

/**
 * Unit Testing the controller with a stand alone setup using MockMVC to mimic
 * an API call to the controller along with mock a service. Includes: Two demo
 * test cases
 */
@WebMvcTest(controllers = SearchController.class)
public class SearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SearchService searchService;

	private static final String TEST_ENDPOINT_COMBI = "/search/api/v1/combi";

	@Test
	@DisplayName("Given a call to an API end poin to get combi search, we expect 200 Status with mock service test data")
	void getAllCustomersTest() throws Exception {

		given(searchService.combiSearch(Mockito.anyString())).willReturn(getCombiResults());
		MvcResult mvcResult = mockMvc.perform(get(TEST_ENDPOINT_COMBI).param("term", Mockito.anyString()))
				.andExpect(request().asyncStarted()).andDo(MockMvcResultHandlers.log()).andReturn();

		mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(getCombiResults().block().size())));

		Mockito.verify(searchService, Mockito.times(1)).combiSearch(Mockito.anyString());
	}

	Mono<List<ApiResponse>> getCombiResults() {
		List<ApiResponse> list = new ArrayList<>();
		list.add(new ApiResponse("title1", null, "BOOK"));
		list.add(new ApiResponse("title2", null, "ALBUM"));
		return Mono.just(list);
	}

}
