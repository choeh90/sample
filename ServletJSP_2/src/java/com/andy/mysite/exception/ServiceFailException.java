package com.andy.mysite.exception;

public class ServiceFailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceFailException(Exception src) {
		super(src);
	}
	public ServiceFailException(String msg) {
		super(msg);
	}
}
