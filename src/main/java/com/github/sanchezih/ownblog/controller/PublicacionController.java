package com.github.sanchezih.ownblog.controller;

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

import com.github.sanchezih.ownblog.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.entity.Publicacion;
import com.github.sanchezih.ownblog.service.PublicacionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/publicaciones")
public class PublicacionController {

	@Autowired
	private PublicacionService publicacionService;

	/*----------------------------------------------------------------------------*/

	@Operation(summary = "Crear una publicacion")
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody PublicacionRequestDTO publicacionRequestDTO) {
		Publicacion publicacionCreada = publicacionService.create(publicacionRequestDTO);
		return new ResponseEntity<>(publicacionCreada, HttpStatus.CREATED);
	}

	/**
	 * El nombre del parametro es publicacionId para ejemplificar el uso del name de
	 * PathVariable.
	 * 
	 * @param publicacionId
	 * @return
	 */
	@Operation(summary = "Obtener una publicacion")
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable(name = "id") Long publicacionId) {
		Publicacion publicacion = publicacionService.getOne(publicacionId);
		return ResponseEntity.ok(publicacion);
	}

	@Operation(summary = "Obtener todas las publicaciones existentes al momento")
	@GetMapping
	public ResponseEntity<?> getAll(Pageable pageable) {
		Page<Publicacion> res = publicacionService.getAll(pageable);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@Operation(summary = "Actualizar una publicacion")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PublicacionRequestDTO publicacionRequestDTO,
			@PathVariable(name = "id") Long idPublicacion) {
		Publicacion res = publicacionService.update(publicacionRequestDTO, idPublicacion);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@Operation(summary = "Eliminar una publicacion")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		publicacionService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
