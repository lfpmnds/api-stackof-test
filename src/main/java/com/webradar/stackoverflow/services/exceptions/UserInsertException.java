package com.webradar.stackoverflow.services.exceptions;

public class UserInsertException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserInsertException(String msg) {
		super(msg);
	}

}
