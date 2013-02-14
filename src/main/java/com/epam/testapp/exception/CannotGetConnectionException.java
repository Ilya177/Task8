package com.epam.testapp.exception;

public final class CannotGetConnectionException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public CannotGetConnectionException() {
	}

	public CannotGetConnectionException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public CannotGetConnectionException(String string) {
		super(string);		
	}

	public CannotGetConnectionException(Throwable throwable) {
		super(throwable);
	}
}
