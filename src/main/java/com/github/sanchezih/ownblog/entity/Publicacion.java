package com.github.sanchezih.ownblog.entity;

import java.util.ArrayList;
import java.util.List;
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
	@OneToMany( //
			mappedBy = "publicacion", //
			cascade = CascadeType.ALL, // Indica que todas las operaciones que se realicen sobre la entidad principal
										// también se aplicarán automáticamente a las entidades relacionadas.

			orphanRemoval = true // Elimina automáticamente las entidades "huérfanas" de la base de datos. Una
									// entidad "huérfana" es aquella que ha sido eliminada de una relación sin ser
									// explícitamente eliminada de la base de datos.
	)

	private List<Comentario> comentarios = new ArrayList<>();

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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}
