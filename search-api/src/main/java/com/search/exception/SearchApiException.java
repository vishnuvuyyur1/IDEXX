package com.search.exception;

/**
 * Customer exception class
 *
 */
public class SearchApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SearchApiException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public SearchApiException(String errorMessage) {
		super(errorMessage);
	}
}
