package com.search.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.search.model.ExceptionResponse;

@ControllerAdvice
public class SearchExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(SearchApiException.class)
	public final ResponseEntity<?> handleCustomerDetailsApiException(SearchApiException ex,
			WebRequest request) {
		 ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
			        request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		 ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
			        request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
