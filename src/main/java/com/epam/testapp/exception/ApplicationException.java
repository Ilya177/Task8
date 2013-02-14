package com.epam.testapp.exception;

public class ApplicationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ApplicationException() {
	}

	public ApplicationException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public ApplicationException(String string) {
		super(string);		
	}

	public ApplicationException(Throwable throwable) {
		super(throwable);
	}
}
