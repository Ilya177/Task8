package com.epam.testapp.exception;

public final class CannotGetListJobsException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public CannotGetListJobsException() {
	}

	public CannotGetListJobsException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public CannotGetListJobsException(String string) {
		super(string);		
	}

	public CannotGetListJobsException(Throwable throwable) {
		super(throwable);
	}
}
