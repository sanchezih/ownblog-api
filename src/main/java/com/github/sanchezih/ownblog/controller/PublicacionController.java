package com.github.sanchezih.ownblog.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.sanchezih.ownblog.dto.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.dto.PublicacionResponseDTO;
import com.github.sanchezih.ownblog.entity.Publicacion;
import com.github.sanchezih.ownblog.service.PublicacionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/publicaciones")
public class PublicacionController {

	private static final String NUMERO_DE_PAGINA_POR_DEFECTO = "0";
	private static final String MEDIDA_DE_PAGINA_POR_DEFECTO = "10";
	private static final String ORDENAR_POR_DEFECTO = "id";
	private static final String ORDENAR_DIRECCION_POR_DEFECTO = "asc";

	@Autowired
	private PublicacionService publicacionService;

	/*----------------------------------------------------------------------------*/

	/**
	 * 
	 * @param publicacionRequestDTO
	 * @return
	 */
	@Operation(summary = "Crear una publicacion")
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody PublicacionRequestDTO publicacionRequestDTO) {

		Publicacion res = publicacionService.create(publicacionRequestDTO);

		return new ResponseEntity<>(res, HttpStatus.CREATED);

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

	/**
	 * 
	 * @param medidaDePagina
	 * @param numeroDePagina
	 * @param ordenarPor
	 * @param sortDir
	 * @return
	 */
	@Operation(summary = "Obtiene todas las publicaciones existentes al momento")
	@GetMapping
	public PublicacionResponseDTO getAll(

			@RequestParam(value = "pageSize", defaultValue = MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value = "pageNo", defaultValue = NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "sortBy", defaultValue = ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

		return publicacionService.getAll(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
	}

	/**
	 * 
	 * @param publicacionRequestDTO
	 * @param idPublicacion
	 * @return
	 */
	@Operation(summary = "Actualizar una publicacion")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PublicacionRequestDTO publicacionRequestDTO,
			@PathVariable(name = "id") Long idPublicacion) {

		Publicacion res = publicacionService.update(publicacionRequestDTO, idPublicacion);

		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Operation(summary = "Eliminar una publicacion")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		publicacionService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
