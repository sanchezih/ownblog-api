package com.github.sanchezih.ownblog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "publicacion", uniqueConstraints = { @UniqueConstraint(columnNames = { "titulo" }) })
public class Publicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_publicacion")
	private Long id;

	@Column(name = "titulo_publicacion", nullable = false)
	private String titulo;

	@Column(name = "contenido_publicacion", nullable = false)
	private String contenido;

	/*----------------------------------------------------------------------------*/

	/**
	 * En necesario que este el constructor por default, caso contrario obtendriamos
	 * una JpaSystemException
	 */
	public Publicacion() {
	}

	public Publicacion(Long id, String titulo, String nombre) {
		this.id = id;
		this.titulo = titulo;
		this.contenido = nombre;

	}

	/*----------------------------------------------------------------------------*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
