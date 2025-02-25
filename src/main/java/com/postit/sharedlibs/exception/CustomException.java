package com.postit.sharedlibs.exception;

import com.postit.sharedlibs.domain.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class CustomException extends RuntimeException {
	private final ErrorCode errorCode;
}
