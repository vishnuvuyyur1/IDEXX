package com.search.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
	
	private String title;
	private List<String> authors;
	private String type;

}
