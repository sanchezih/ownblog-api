package com.github.sanchezih.ownblog.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.sanchezih.ownblog.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.dto.response.PublicacionResponseDTO;
import com.github.sanchezih.ownblog.service.PublicacionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/publicaciones")
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
	 * @param medidaDePagina
	 * @param numeroDePagina
	 * @param ordenarPor
	 * @param sortDir
	 * @return
	 */
	@ApiOperation(value = "Obtener todas las publicaciones", notes = "Retorna todas las publicaciones existentes al momento")
	@GetMapping
	public PublicacionResponseDTO getAllPublicaciones(

			@RequestParam(value = "pageSize", defaultValue = MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value = "pageNo", defaultValue = NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "sortBy", defaultValue = ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

		return publicacionService.getAllPublicaciones(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getPublicacion(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(publicacionService.getOneById(id));
	}

	/**
	 * 
	 * @param publicacionDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<PublicacionRequestDTO> createPublicacion(
			@Valid @RequestBody PublicacionRequestDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionService.createPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param publicacionDTO
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionRequestDTO> updatePublicacion(
			@Valid @RequestBody PublicacionRequestDTO publicacionDTO, @PathVariable(name = "id") long id) {
		PublicacionRequestDTO publicacionRespuesta = publicacionService.updatePublicacion(publicacionDTO, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePublicacion(@PathVariable(name = "id") long id) {
		publicacionService.deletePublicacion(id);
		return ResponseEntity.noContent().build();
	}
}
