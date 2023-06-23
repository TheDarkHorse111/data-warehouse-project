package com.suliman.datawarehouse.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.suliman.datawarehouse.wrapper.ErrorResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GloableExceptionHandler {
	private static final Logger logger = LogManager.getLogger(GloableExceptionHandler.class);

	@ExceptionHandler(NoSuchDealException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NoSuchDealException e) {
		logger.error("NoSuchDealException: ", e);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(404, e.getMessage(), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getSimpleName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}
		logger.error("ConstraintViolationException: ", e);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"Constraint Validation error: " + errors, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException e) {
		logger.error("MethodArgumentTypeMismatchException: ", e);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"URI Path Variable must be a number", LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		logger.error("HttpRequestMethodNotSupportedException: ", e);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(),
				"Method Not Allowed for this URL", LocalDateTime.now()), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logger.error("HttpMessageNotReadableException: ", e);
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Date format", LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

}
