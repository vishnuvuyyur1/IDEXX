package com.search.service;

import java.util.List;

import com.search.model.ApiResponse;

import reactor.core.publisher.Mono;

/**
 * Interface exposing the operations
 *
 */
public interface ISearchService {

	public Mono<List<ApiResponse>> combiSearch(String term);

	public Mono<List<ApiResponse>> searchForBook(String term);

	public Mono<List<ApiResponse>> searchForAlbums(String term);
}
