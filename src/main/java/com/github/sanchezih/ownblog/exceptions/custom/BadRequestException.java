package com.github.sanchezih.ownblog.exceptions.custom;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "Bad Request Exception (400)";

	/**
	 * En el constructor recibo los detalles
	 * 
	 * @param detail
	 */
	public BadRequestException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
