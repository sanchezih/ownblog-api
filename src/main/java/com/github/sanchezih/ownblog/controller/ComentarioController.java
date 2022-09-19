package com.github.sanchezih.ownblog.controller;

import java.util.List;

import javax.validation.Valid;

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

@RestController
@RequestMapping("/api/publicaciones/{publicacionId}/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	/*----------------------------------------------------------------------------*/

	/**
	 * 
	 * @param publicacionId
	 * @param comentarioDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Comentario> createComentario(@PathVariable(value = "publicacionId") long publicacionId,
			@Valid @RequestBody ComentarioRequestDTO comentarioDTO) {
		return new ResponseEntity<>(comentarioService.createComentario(publicacionId, comentarioDTO),
				HttpStatus.CREATED);
	}

	/**
	 * Metodo que retorna un comentario en base a su id
	 * 
	 * @param publicacionId
	 * @param comentarioId
	 * @return
	 */
	@GetMapping("/{id}")
	public Comentario getComentarioById(@PathVariable(value = "publicacionId") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId) {
		Comentario comentario = comentarioService.getComentarioById(publicacionId, comentarioId);
		return comentario;
	}

	/**
	 * 
	 * @param publicacionId
	 * @return
	 */
	@GetMapping
	public List<Comentario> getAllComentariosByPublicacionId(
			@PathVariable(value = "publicacionId") Long publicacionId) {
		return comentarioService.getAllComentariosByPublicacionId(publicacionId);
	}

	/**
	 * 
	 * @param publicacionId
	 * @param comentarioId
	 * @param comentarioDTO
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ComentarioRequestDTO> updateComentario(
			@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "id") Long comentarioId,
			@Valid @RequestBody ComentarioRequestDTO comentarioDTO) {
		ComentarioRequestDTO comentarioActualizado = comentarioService.updateComentario(publicacionId, comentarioId,
				comentarioDTO);
		return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
	}

	/**
	 * Metodo que elimina un comentario de una publicacion
	 * 
	 * @param publicacionId
	 * @param comentarioId
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarComentario(@PathVariable Long publicacionId,
			@PathVariable(value = "id") Long comentarioId) {
		comentarioService.deleteComentario(publicacionId, comentarioId);
		return ResponseEntity.noContent().build();
	}
}
