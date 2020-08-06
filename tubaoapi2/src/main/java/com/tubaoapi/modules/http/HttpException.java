package com.tubaoapi.modules.http;


public class HttpException extends Exception {

	private static final long serialVersionUID = -146211020412303998L;

	public HttpException() {
		super();
	}

	public HttpException(String message) {
		super(message);
	}

	public HttpException(Throwable cause) {
		super(cause);
	}

	public HttpException(String message, Throwable cause) {
		super(message, cause);
	}
}
