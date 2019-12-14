package com.rabo.app.exception;

public class DataValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataValidationException(String message) {
		super("Data not available");
	}

}
