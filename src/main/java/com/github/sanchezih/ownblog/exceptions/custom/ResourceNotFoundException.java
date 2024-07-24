package com.github.sanchezih.ownblog.exceptions.custom;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "NotFound Exception (404)";

	private String nombreDelRecurso;
	private String nombreDelCampo;
	private long valorDelCampo;

	public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, long valorDelCampo) {

		super(DESCRIPTION + ". "
				+ String.format("%s no existente: %s='%s'", nombreDelRecurso, nombreDelCampo, valorDelCampo));

		this.nombreDelRecurso = nombreDelRecurso;
		this.nombreDelCampo = nombreDelCampo;
		this.valorDelCampo = valorDelCampo;
	}

}
