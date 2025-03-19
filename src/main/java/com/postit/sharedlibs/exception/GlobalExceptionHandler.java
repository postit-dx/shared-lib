package com.postit.sharedlibs.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.postit.sharedlibs.domain.ErrorCode;
import com.postit.sharedlibs.domain.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 커스텀 예외 처리
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
		log.error("CustomException: {}", e.getMessage(), e);
		return handleExceptionInternal(e);
	}

	// 400 BAD_REQUEST: 잘못된 요청 처리
	@ExceptionHandler({
		MethodArgumentNotValidException.class,
		MethodArgumentTypeMismatchException.class,
		HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class,
		IllegalArgumentException.class
	})
	public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e) {
		log.error("Bad Request Exception: {}", e.getMessage(), e);
		return handleExceptionInternal(ErrorCode.BAD_REQUEST);
	}

	// 403 FORBIDDEN: 권한이 없는 사용자 요청 처리
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleForbiddenException(AccessDeniedException e) {
		log.error("Forbidden Exception: {}", e.getMessage(), e);
		return handleExceptionInternal(ErrorCode.FORBIDDEN_ACCESS);
	}

	// 404 NOT_FOUND: 리소스를 찾을 수 없음
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NoHandlerFoundException e) {
		log.error("Not Found Exception: {}", e.getMessage(), e);
		return handleExceptionInternal(ErrorCode.NOT_FOUND);
	}

	// 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e) {
		log.error("Method Not Allowed Exception: {}", e.getMessage(), e);
		return handleExceptionInternal(ErrorCode.METHOD_NOT_ALLOWED);
	}

	// 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("Internal Server Error: {}", e.getMessage(), e);
		return handleExceptionInternal(ErrorCode.INTERNAL_SERVER_ERROR_NO_RESOLUTION);
	}

	protected ResponseEntity<ErrorResponse> handleExceptionInternal(ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus().value())
			.body(new ErrorResponse(errorCode));
	}

	protected ResponseEntity<ErrorResponse> handleExceptionInternal(CustomException errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus().value())
			.body(new ErrorResponse(errorCode));
	}
}
