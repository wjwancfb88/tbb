package com.dhwooden.ep.base;

public class APIException extends RuntimeException {

	private static final long serialVersionUID = -2885762187639469955L;
	
	

	public APIException() {
		super();
	}

	public APIException(String message) {
		super(message);
	}

	public APIException(Throwable cause) {
		super(cause);
	}

	public APIException(String message, Throwable cause) {
		super(message, cause);
	}


}
