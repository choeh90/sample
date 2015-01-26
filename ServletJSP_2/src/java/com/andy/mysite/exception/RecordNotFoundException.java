package com.andy.mysite.exception;

public class RecordNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String key) {
		super(key + "에 해당하는 자료를 찾을 수 없습니다.");
	}
}
