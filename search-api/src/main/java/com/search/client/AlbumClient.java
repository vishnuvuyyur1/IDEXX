package com.search.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

@Component
public class AlbumClient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final AlbumClient albumClient = new AlbumClient();
	
	public String get(String link) throws IOException {
		URL url = new URL(link);
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.UTF_8))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			return sb.toString().trim();
		}
	}

}
