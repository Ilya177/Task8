package com.epam.testapp.exception;

public final class CannotGetListEmployeesException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public CannotGetListEmployeesException() {
	}

	public CannotGetListEmployeesException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public CannotGetListEmployeesException(String string) {
		super(string);		
	}

	public CannotGetListEmployeesException(Throwable throwable) {
		super(throwable);
	}
}
