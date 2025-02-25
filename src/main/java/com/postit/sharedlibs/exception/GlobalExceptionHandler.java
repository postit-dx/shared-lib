package com.postit.sharedlibs.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.postit.sharedlibs.domain.ErrorCode;
import com.postit.sharedlibs.domain.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
		log.error(e.getMessage(), e);
		return handleExceptionInternal(e.getErrorCode());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return handleExceptionInternal(ErrorCode.INTERNAL_SERVER_ERROR_NO_RESOLUTION);
	}

	private ResponseEntity<ErrorResponse> handleExceptionInternal(ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus().value())
			.body(new ErrorResponse(errorCode));
	}
}
