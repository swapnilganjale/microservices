package com.project.userservice.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@Autowired
	private CustomExceptionCreatorService customException;
	
	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(CustomException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getErrorMessage(),
				ex.getDescription(), ex.getStatusCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		
		if(ex.getBindingResult().getFieldError().getField().equalsIgnoreCase("username")) {
			 
			 return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getBindingResult().getFieldError().getDefaultMessage(),
					 ex.getBindingResult().getFieldError().getDefaultMessage(),"000209"), HttpStatus.BAD_REQUEST);
			 
		}else {
			CustomException exception  = customException.getCustomException("000401", getErroFields(ex).toString());
			 
			 return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getErrorMessage(),
						exception.getDescription(), exception.getStatusCode()), HttpStatus.BAD_REQUEST);
		}
		

		
	}


	private StringBuffer getErroFields(MethodArgumentNotValidException ex) {
		StringBuffer errorFields = new StringBuffer();
		if (ex.getBindingResult().getFieldErrors() != null) {
			if (ex.getBindingResult().getFieldErrors().size() > 1) {
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					errorFields.append(" "+error.getField()+",");
				}
			} else {
				errorFields.append(ex.getBindingResult().getFieldErrors().get(0).getField());
			}
			return errorFields;
		} else {
			return null;
		}
	}
}