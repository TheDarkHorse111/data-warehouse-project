package com.suliman.datawarehouse.exception;

public class NoSuchDealException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchDealException() {
		super();
	}

	public NoSuchDealException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchDealException(String message) {
		super(message);
	}

}
