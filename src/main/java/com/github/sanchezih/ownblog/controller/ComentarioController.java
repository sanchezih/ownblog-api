package com.github.sanchezih.ownblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sanchezih.ownblog.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.ownblog.entity.Comentario;
import com.github.sanchezih.ownblog.service.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/publicaciones/{publicacionId}/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	/*----------------------------------------------------------------------------*/

	@Operation(summary = "Crear un comentario")
	@PostMapping
	public ResponseEntity<?> create(@PathVariable(name = "publicacionId") Long pubId,
			@Valid @RequestBody ComentarioRequestDTO comentarioRequestDTO) {

		Comentario comentarioCreado = comentarioService.create(pubId, comentarioRequestDTO);
		return new ResponseEntity<>(comentarioCreado, HttpStatus.CREATED);
	}

	@Operation(summary = "Obtener un comentario")
	@GetMapping("/{comentarioId}")
	public ResponseEntity<?> getOne(@PathVariable(name = "publicacionId") Long pubId,
			@PathVariable(value = "comentarioId") Long comenId) {

		Comentario comentario = comentarioService.getOne(pubId, comenId);
		return new ResponseEntity<>(comentario, HttpStatus.OK);
	}

	@Operation(summary = "Obtener todos los comentarios pertenecientes a una publicacion existentes al momento")
	@GetMapping
	public ResponseEntity<?> getAll(@PathVariable(value = "publicacionId") Long pubId, Pageable pageable) {
		Page<Comentario> comentarios = comentarioService.getAllComentariosByPublicacionId(pubId, pageable);
		return new ResponseEntity<>(comentarios, HttpStatus.OK);
	}

	@Operation(summary = "Actualizar un comentario")
	@PutMapping("/{comentarioId}")
	public ResponseEntity<?> update(@PathVariable(name = "publicacionId") Long pubId,
			@PathVariable(value = "comentarioId") Long comentarioId,
			@Valid @RequestBody ComentarioRequestDTO comentarioDTO) {

		ComentarioRequestDTO comentarioActualizado = comentarioService.updateComentario(pubId, comentarioId,
				comentarioDTO);
		return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
	}

	@Operation(summary = "Eliminar un comentario")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long publicacionId, @PathVariable(name = "id") Long comentarioId) {
		comentarioService.delete(publicacionId, comentarioId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
