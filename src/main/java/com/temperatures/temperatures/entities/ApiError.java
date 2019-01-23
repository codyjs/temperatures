package com.temperatures.temperatures.entities;

/**
 * Contains an error message and an HTTP error code.
 * @author Cody
 *
 */
public class ApiError {
	private String message;
	private int errorCode;

	public ApiError(String message, int errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
