/**
 * <h1>To manage custom</h1>
 * 
 * @author Vishal
 * @see Exception
 */
package com.project.userservice.exception;


public class CustomException extends Exception {


	private final String errorMessage;
	private final String description;
	private final String statusCode;

	public CustomException(String errorMessage, String description, String statusCode) {
		super();
		this.errorMessage = errorMessage;
		this.description = description;
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getDescription() {
		return description;
	}

}
