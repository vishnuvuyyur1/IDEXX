package com.search.controller;

import java.io.File;
import java.nio.file.Files;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.exception.SearchApiException;

@RestController
@RequestMapping("/search/api/v1")
public class GarageController {

	@GetMapping("/garage")
	public JSONArray getCars() {
		try {
			File resource = new ClassPathResource("garage.json").getFile();
			String warehouses = new String(Files.readAllBytes(resource.toPath()));
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(warehouses);
			return json;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SearchApiException(ex.getMessage(), ex);
		}
	}
}
