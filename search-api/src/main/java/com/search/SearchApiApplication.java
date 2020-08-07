package com.search;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SearchApiApplication {

	@Value("${server.cors.mapping}")
	String mapping;
	@Value("#{'${server.cors.allowedorigins}'.split(',')}")
	String[] allowedOrigins;
	@Value("#{'${server.cors.allowedmethods}'.split(',')}")
	String[] allowedMethods;
	@Value("#{'${server.cors.allowedheaders}'.split(',')}")
	String[] allowedHeaders;

	public static void main(String[] args) {
		SpringApplication.run(SearchApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping(mapping).allowedMethods(allowedMethods).allowedOrigins(allowedOrigins)
						.allowedHeaders(allowedHeaders).allowCredentials(true);
			}
		};
	}

}
