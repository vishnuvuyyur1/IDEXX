package com.search.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.search.client.AlbumClient;
import com.search.mapper.ResponseMapper;
import com.search.model.AlbumResponse;
import com.search.model.ApiResponse;
import com.search.model.BookResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SearchService implements ISearchService {
	@Autowired
	AlbumClient albumClient;

	@Autowired
	ResponseMapper responseMapper;

	@Value("${search.results.limt}")
	private int resultLimit;
	
	private final WebClient booksClient;
	
	private static final String BOOKS_API = "https://www.googleapis.com/books";
	private static final String BOOKS_API_PATH = "/v1/volumes";
	private static final String BOOKS_API_PARAM_TERM = "q";
	private static final String BOOKS_API_PARAM_LIMIT = "maxResults";
	private static final String ALBUMS_API = "https://itunes.apple.com/search";
	private static final String ALBUMS_API_PARAM_TERM = "term";
	private static final String ALBUMS_API_PARAM_LIMIT = "limit";
	
	public SearchService(WebClient.Builder webClientBuilder) {
		this.booksClient = webClientBuilder.baseUrl(BOOKS_API).build();
	}

	@Override
	public Mono<List<ApiResponse>> combiSearch(String term) {
		 Comparator<ApiResponse> comparator
	      = (h1, h2) -> h1.getTitle().toLowerCase().compareTo(h2.getTitle().toLowerCase());
		return Mono.zip(searchForBook(term), searchForAlbums(term), (book, album) -> {
			List<ApiResponse> resp = new ArrayList<>();
			resp.addAll(book);
			resp.addAll(album);
			return resp.stream().sorted(comparator).collect(Collectors.toList());
		});
	}

	@Override
	public Mono<List<ApiResponse>> searchForBook(String term) {
		log.info("Search for book started");
		return this.booksClient.get()
				.uri(uriBuilder -> uriBuilder.path(BOOKS_API_PATH).queryParam(BOOKS_API_PARAM_TERM, term)
						.queryParam(BOOKS_API_PARAM_LIMIT, resultLimit).build())
				.retrieve().bodyToMono(BookResponse.class)
				.map(book -> responseMapper.mapFromBookResponse(book));
	}

	@Override
	public Mono<List<ApiResponse>> searchForAlbums(String term) {
		log.info("Search for album started");
		try {
			String url = UriComponentsBuilder.fromUriString(ALBUMS_API).queryParam(ALBUMS_API_PARAM_TERM, term)
					.queryParam(ALBUMS_API_PARAM_LIMIT, resultLimit).build().toUriString();
			String response = albumClient.get(url);
			Mono<AlbumResponse> albumResp= Mono.just(AlbumResponse.READER.readValue(response));
			return albumResp.map(album -> responseMapper.mapFromAlbumResponse(album));
		} catch (IOException e) {
			log.error(e.getMessage());;
		}
		return null;
	}

}
