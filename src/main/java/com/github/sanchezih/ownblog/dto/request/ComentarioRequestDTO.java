package com.github.sanchezih.ownblog.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ComentarioRequestDTO {

	@NotEmpty(message = "El nombre no debe ser vacio o nulo")
	private String nombre;

	@NotEmpty(message = "El email no debe ser vacio o nulo")
	@Email
	private String email;

	@NotEmpty
	@Size(min = 10, message = "El cuerpo del comentario debe tener al menos 10 caracteres")
	private String cuerpo;

	public ComentarioRequestDTO(@NotEmpty(message = "El nombre no debe ser vacio o nulo") String nombre,
			@NotEmpty(message = "El email no debe ser vacio o nulo") @Email String email,
			@NotEmpty @Size(min = 10, message = "El cuerpo del comentario debe tener al menos 10 caracteres") String cuerpo) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.cuerpo = cuerpo;
	}

	public ComentarioRequestDTO() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

}
