package com.epam.testapp.exception;

public final class CannotGeTotalCountEmployeesException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public CannotGeTotalCountEmployeesException() {
	}

	public CannotGeTotalCountEmployeesException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public CannotGeTotalCountEmployeesException(String string) {
		super(string);		
	}

	public CannotGeTotalCountEmployeesException(Throwable throwable) {
		super(throwable);
	}
}
