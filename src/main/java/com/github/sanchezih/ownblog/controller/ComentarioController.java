package com.github.sanchezih.ownblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/v1/publicaciones/{publicacionId}/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	/*----------------------------------------------------------------------------*/

	/**
	 * 
	 * @param publicacionId
	 * @param comentarioRequestDTO
	 * @return
	 */
	@Operation(summary = "Crear un comentario")
	@PostMapping
	public ResponseEntity<?> create(@PathVariable(value = "publicacionId") long publicacionId,
			@Valid @RequestBody ComentarioRequestDTO comentarioRequestDTO) {

		Comentario res = comentarioService.create(publicacionId, comentarioRequestDTO);

		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param publicacionId
	 * @param comentarioId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable(value = "publicacionId") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId) {

		Comentario comentario = comentarioService.getOne(publicacionId, comentarioId);
		return ResponseEntity.ok(comentario);
	}

	/**
	 * 
	 * @param publicacionId
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<Comentario>> getAll(@PathVariable(value = "publicacionId") Long publicacionId) {

		List<Comentario> comentarios = comentarioService.getAllComentariosByPublicacionId(publicacionId);

		return !comentarios.isEmpty() ? ResponseEntity.ok(comentarios) : ResponseEntity.noContent().build();

	}

	/**
	 * 
	 * @param publicacionId
	 * @param comentarioId
	 * @param comentarioDTO
	 * @return
	 */
	@Operation(summary = "Actualizar un comentario")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "publicacionId") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId, @Valid @RequestBody ComentarioRequestDTO comentarioDTO) {

		ComentarioRequestDTO comentarioActualizado = comentarioService.updateComentario(publicacionId, comentarioId,
				comentarioDTO);
		return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
	}

	/**
	 * 
	 * @param publicacionId
	 * @param comentarioId
	 * @return
	 */
	@Operation(summary = "Eliminar un comentario")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long publicacionId, @PathVariable(value = "id") Long comentarioId) {
		comentarioService.delete(publicacionId, comentarioId);
		return ResponseEntity.noContent().build();
	}
}
