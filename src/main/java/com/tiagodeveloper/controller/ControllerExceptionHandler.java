package com.tiagodeveloper.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tiagodeveloper.exception.BadRequestException;
import com.tiagodeveloper.exception.NotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Map<String, Object>> resourceNotFoundException(RuntimeException ex, WebRequest request) {

		var response = new HashMap<String, Object>();
			response.put("message", ex.getMessage());
			response.put("code", HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, Object>> resourceBadRequestException(RuntimeException ex, WebRequest request) {

		var response = new HashMap<String, Object>();
			response.put("message", ex.getMessage());
			response.put("code", HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
