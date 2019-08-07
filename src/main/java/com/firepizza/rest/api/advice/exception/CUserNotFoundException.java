package com.firepizza.rest.api.advice.exception;

/**
 * 유저 조회 실패시 발생시킬 예외
 */
public class CUserNotFoundException extends RuntimeException {

	public CUserNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public CUserNotFoundException(String msg) {
		super(msg);
	}

	public CUserNotFoundException() {
		super();
	}

}
