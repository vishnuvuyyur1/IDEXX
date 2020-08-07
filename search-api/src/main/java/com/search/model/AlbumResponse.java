package com.search.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumResponse {
	private static final ObjectMapper objectMapper = 
		    new ObjectMapper()
		        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	public static final ObjectReader READER = objectMapper.readerFor(AlbumResponse.class);
	private List<Result> results;
}
