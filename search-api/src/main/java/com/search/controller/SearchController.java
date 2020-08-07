package com.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.exception.SearchApiException;
import com.search.model.ApiResponse;
import com.search.service.SearchService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Controller layer to handle HTTP requests
 *
 */
@Slf4j
@RestController
@RequestMapping("/search/api/v1")
public class SearchController {

	private static final String REQUEST_PARAM_TERM = "term";
	private static final String PATH_BOOK = "/books";
	private static final String PATH_ALBUMS = "/albums";
	private static final String PATH_COMBI = "/combi";

	@Autowired
	private SearchService searchService;

	@GetMapping(PATH_BOOK)
	public Mono<List<ApiResponse>> getBooks(@RequestParam(name = REQUEST_PARAM_TERM, required = true) String term) {
		try {
			return searchService.searchForBook(term);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new SearchApiException(ex.getMessage(), ex);
		}
	}

	@GetMapping(PATH_ALBUMS)
	public Mono<List<ApiResponse>> getAlbums(@RequestParam(name = REQUEST_PARAM_TERM, required = true) String term) {
		try {
			return searchService.searchForAlbums(term);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new SearchApiException(ex.getMessage(), ex);
		}
	}

	@GetMapping(PATH_COMBI)
	public Mono<List<ApiResponse>> getBooksAndAlbums(
			@RequestParam(name = REQUEST_PARAM_TERM, required = true) String term) {
		try {
			return searchService.combiSearch(term);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new SearchApiException(ex.getMessage(), ex);
		}
	}
}
