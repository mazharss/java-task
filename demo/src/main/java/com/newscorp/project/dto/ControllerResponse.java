package com.newscorp.project.dto;

import java.util.List;

/**
 * This is the response object that will be returned with every API call.
 * 
 * @author mazharshaikh
 */
public class ControllerResponse {

	/**
	 * Denotes the status of response status message. This will be limited to
	 * standard 4-5 HTTP status code
	 */
	private int statusCode;

	// Contains success response details
	private Object successResult;

	/**
	 * Contains the error response detail. This will be populated only in case
	 * of error
	 */
	private List<ErrorDTO> errors;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getSuccessResult() {
		return successResult;
	}

	public void setSuccessResult(Object successResult) {
		this.successResult = successResult;
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDTO> errors) {
		this.errors = errors;
	}
}
