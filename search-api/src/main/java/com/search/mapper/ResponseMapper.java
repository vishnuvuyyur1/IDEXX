package com.search.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.search.model.AlbumResponse;
import com.search.model.ApiResponse;
import com.search.model.BookResponse;
import com.search.model.Type;

import lombok.extern.slf4j.Slf4j;

/**
 * Mapper to map from google books api and itunes api to custom model
 *
 */
@Slf4j
@Component
public class ResponseMapper {

	public List<ApiResponse> mapFromBookResponse(BookResponse bookResp) {
		log.info("Mapping for book started");
		if (bookResp.getItems() != null) {
			return bookResp.getItems().stream().map(book -> new ApiResponse(book.getVolumeInfo().getTitle(),
					book.getVolumeInfo().getAuthors(), Type.BOOK.toString())).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	public List<ApiResponse> mapFromAlbumResponse(AlbumResponse albumResp) {
		log.info("Mapping for album started");
		if (albumResp.getResults() != null) {
			return albumResp.getResults().stream().map(album -> new ApiResponse(album.getWrapperType(),
					Arrays.asList(album.getArtistName()), Type.ALBUM.toString())).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

}
