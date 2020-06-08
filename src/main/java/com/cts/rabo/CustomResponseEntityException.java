package com.cts.rabo;

import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.response.CustomError;
import com.cts.response.CustomValidatorResponse;

@ControllerAdvice
@RestController
public class CustomResponseEntityException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException constraintViolationException) {
	
		ArrayList<CustomError> customErrors=new ArrayList<>();
		CustomValidatorResponse customValidatorException=new CustomValidatorResponse("BAD_REQUEST", customErrors);
		return  new ResponseEntity<>(customValidatorException, HttpStatus.BAD_REQUEST);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		ArrayList<CustomError> customErrors=new ArrayList<>();
		CustomValidatorResponse customValidatorException=new CustomValidatorResponse("BAD_REQUEST", customErrors);
		return  new ResponseEntity<>(customValidatorException, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ArrayList<CustomError> customErrors=new ArrayList<>();
		CustomValidatorResponse customValidatorException=new CustomValidatorResponse("BAD_REQUEST", customErrors);
		return  new ResponseEntity<>(customValidatorException, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	
		
		ArrayList<CustomError> customErrors=new ArrayList<>();
		CustomValidatorResponse customValidatorException=new CustomValidatorResponse("INTERNAL_SERVER_ERROR", customErrors);
		return  new ResponseEntity<>(customValidatorException, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	

		
		
	
}
