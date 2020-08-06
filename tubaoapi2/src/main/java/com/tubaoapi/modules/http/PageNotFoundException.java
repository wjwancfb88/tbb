package com.tubaoapi.modules.http;


public class PageNotFoundException extends Exception {


	private static final long serialVersionUID = -4672539304561747281L;

	public PageNotFoundException() {
		super();
	}

	public PageNotFoundException(String message) {
		super(message);
	}

	public PageNotFoundException(Throwable cause) {
		super(cause);
	}

	public PageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
