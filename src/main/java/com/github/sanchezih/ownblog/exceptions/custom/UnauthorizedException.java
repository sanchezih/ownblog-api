package com.github.sanchezih.ownblog.exceptions.custom;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "Unauthorized Exception (401)";

	public UnauthorizedException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
