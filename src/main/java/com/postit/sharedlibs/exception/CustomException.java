package com.postit.sharedlibs.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class CustomException extends RuntimeException {
	private final HttpStatus httpStatus;
	private final String message;
}
