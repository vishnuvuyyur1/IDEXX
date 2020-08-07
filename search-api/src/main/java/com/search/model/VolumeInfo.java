package com.search.model;

import java.util.List;

import lombok.Data;

@Data
public class VolumeInfo {
	private String title;
    private List<String> authors;
    private String printType;
}
