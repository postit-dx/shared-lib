package com.postit.sharedlibs.domain;

import java.time.LocalDateTime;

import com.postit.sharedlibs.exception.CustomException;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private final LocalDateTime timestamp = LocalDateTime.now();
	private final int statusCode;
	private final String error;
	private final String message;

	public ErrorResponse(ErrorCode errorCode) {
		this.statusCode = errorCode.getHttpStatus().value();
		this.error = errorCode.getHttpStatus().name();
		this.message = errorCode.getMessage();
	}

	public ErrorResponse(CustomException customException) {
		this.statusCode = customException.getHttpStatus().value();
		this.error = customException.toString();
		this.message = customException.getMessage();
	}
}
