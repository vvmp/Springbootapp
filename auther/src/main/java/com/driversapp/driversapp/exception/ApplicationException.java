package com.driversapp.driversapp.exception;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 1L;

	private final long status;

	private final String message;

	private final String error;
	
	public ApplicationException(final long status,final String message,final String error) {
		this.status = status;
		this.message = message;
		this.error = error;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}
}
