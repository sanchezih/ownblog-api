package com.github.sanchezih.ownblog.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
//@Table(name = "publicacion", uniqueConstraints = {@UniqueConstraint(columnNames = { "titulo_publicacion", "contenido_publicacion" }) })

@Table(name = "publicacion")
public class Publicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_publicacion")
	private Long id;

	// No se permite que se repita el titulo de una publicacion
	@Column(name = "titulo_publicacion", nullable = false, unique = true)
	private String titulo;

	@Column(name = "contenido_publicacion", nullable = false)
	private String contenido;

	@JsonBackReference
	@OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comentario> comentarios = new HashSet<>();

	/*----------------------------------------------------------------------------*/

	/**
	 * En necesario que este el constructor por default, caso contrario obtendriamos
	 * una JpaSystemException
	 */
	public Publicacion() {
	}

	public Publicacion(String titulo, String contenido) {
		this.titulo = titulo;
		this.contenido = contenido;

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

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
