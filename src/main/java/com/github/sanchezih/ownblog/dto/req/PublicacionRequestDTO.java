package com.github.sanchezih.ownblog.dto.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PublicacionRequestDTO {

	@NotEmpty
	@Size(min = 3, message = "El titulo de la publicacion debe tener al menos 3 caracteres")
	private String titulo;

	@NotEmpty
	@Size(min = 5, message = "El contenido de la publicacion debe tener al menos 5 caracteres")
	private String contenido;

	/*----------------------------------------------------------------------------*/

	public PublicacionRequestDTO() {
	}

	/*----------------------------------------------------------------------------*/

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
