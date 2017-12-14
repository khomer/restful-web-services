package com.javagal.rest.webservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TestimonialNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TestimonialNotFoundException(String message) {
		super(message);
	}

}
