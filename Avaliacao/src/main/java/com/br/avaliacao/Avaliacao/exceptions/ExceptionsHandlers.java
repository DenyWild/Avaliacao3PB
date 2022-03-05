package com.br.avaliacao.Avaliacao.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionsHandlers {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<MethodArgumentNotValid>> hand(MethodArgumentNotValidException exception) {

		List<MethodArgumentNotValid> mtd = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			MethodArgumentNotValid argumentsNotValid = new MethodArgumentNotValid(e.getField(), message);

			mtd.add(argumentsNotValid);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mtd);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> handle(HttpMessageNotReadableException exception, HttpServletRequest request) {

		exception.getMostSpecificCause().addSuppressed(exception);
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		err.setError("Resource not acceptable");
		err.setMessage("It's not possible insert that value in the region param");
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);

	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> handle(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {

		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		err.setError("Method not Allowed");
		err.setMessage("this region doesnt exist");
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);

	}

	@ExceptionHandler(EntityNotFound.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFound exception, HttpServletRequest request) {

		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not Found");
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ValidationOfTimeSinceFundation.class)
	public ResponseEntity<StandardError> ValidationOfTimeSinceFundation(ValidationOfTimeSinceFundation exception,
			HttpServletRequest request) {

		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		err.setError("Resource not acceptable");
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
	}

}
