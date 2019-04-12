/**
 * <h1>Data transfer object for exception</h1>
 * 
 * @author Vishal
 *
 */
package com.project.userservice.exception;

import java.util.Date;



public class ExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String details;
	private String statusCode;

	public ExceptionResponse(Date timestamp, String message, String details, String statusCode) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.statusCode = statusCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}