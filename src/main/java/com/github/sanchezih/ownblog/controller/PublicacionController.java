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
import com.github.sanchezih.ownblog.util.AppConstantes;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

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
	@GetMapping
	public PublicacionResponseDTO getAllPublicaciones(

			@RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

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
	public ResponseEntity<PublicacionRequestDTO> addPublicacion(@Valid @RequestBody PublicacionRequestDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionService.addPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param publicacionDTO
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionRequestDTO> updatePublicacion(@Valid @RequestBody PublicacionRequestDTO publicacionDTO,
			@PathVariable(name = "id") long id) {
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
		return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
	}
}
