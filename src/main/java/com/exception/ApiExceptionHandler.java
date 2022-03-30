package com.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(value = { CashApiRequestException.class })
	public ResponseEntity<Object> handleApiRequestException(CashApiRequestException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(e.getCause(), badRequest, ZonedDateTime.now(ZoneId.of("Z")),e.getMessage());
		return new ResponseEntity<>(apiException, badRequest);
	}
}
