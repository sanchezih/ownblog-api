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
	 * @return
	 */
	@GetMapping
	public List<ComentarioRequestDTO> listarComentariosPorPublicacionId(
			@PathVariable(value = "publicacionId") Long publicacionId) {
		return comentarioService.obtenerComentariosPorPublicacionId(publicacionId);
	}

	/**
	 * 
	 * @param publicacionId
	 * @param comentarioId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ComentarioRequestDTO> getComentarioById(
			@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "id") Long comentarioId) {
		ComentarioRequestDTO comentarioDTO = comentarioService.getComentarioById(publicacionId, comentarioId);
		return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
	}

	/**
	 * 
	 * @param publicacionId
	 * @param comentarioDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<ComentarioRequestDTO> guardarComentario(@PathVariable(value = "publicacionId") long publicacionId,
			@Valid @RequestBody ComentarioRequestDTO comentarioDTO) {
		return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDTO),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComentarioRequestDTO> actualizarComentario(@PathVariable(value = "publicacionId") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId, @Valid @RequestBody ComentarioRequestDTO comentarioDTO) {
		ComentarioRequestDTO comentarioActualizado = comentarioService.actualizarComentario(publicacionId, comentarioId,
				comentarioDTO);
		return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarComentario(@PathVariable(value = "publicacionId") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId) {
		comentarioService.eliminarComentario(publicacionId, comentarioId);
		return new ResponseEntity<>("Comentario eliminado con exito", HttpStatus.OK);
	}
}
