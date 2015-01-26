package com.andy.mysite.exception;

public class DuplicateKeyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateKeyException(String key) {
		super(key + "는 이미 사용 중입니다.");
	}
}
