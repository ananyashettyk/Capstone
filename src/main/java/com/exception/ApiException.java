package com.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiException {
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timeStamp;
	private final String message;
	public ApiException(Throwable throwable, HttpStatus httpStatus, ZonedDateTime timeStamp,String message) {
		
		this.throwable = throwable;
		this.httpStatus = httpStatus;
		this.timeStamp = timeStamp;
		this.message = message;
	}
}