package com.github.sanchezih.ownblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.sanchezih.ownblog.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.dto.response.PublicacionResponseDTO;
import com.github.sanchezih.ownblog.entity.Publicacion;
import com.github.sanchezih.ownblog.exceptions.custom.BadRequestException;
import com.github.sanchezih.ownblog.exceptions.custom.ResourceNotFoundException;
import com.github.sanchezih.ownblog.repository.PublicacionRepository;
import com.github.sanchezih.ownblog.service.PublicacionService;

@Service
public class PublicacionServiceImpl implements PublicacionService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PublicacionRepository publicacionRepository;

	/*----------------------------------------------------------------------------*/

	/**
	 * 
	 */
	@Override
	public PublicacionRequestDTO createPublicacion(PublicacionRequestDTO publicacionReqDTO) {

		// Se convierte el DTO en entidad
		Publicacion publicacion = mapPublicacionReqDTOToPublicacion(publicacionReqDTO);

		Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);

		PublicacionRequestDTO publicacionRespuesta = mapPublicacionToPublicacionRequestDTO(nuevaPublicacion);
		return publicacionRespuesta;
	}

	/**
	 * 
	 */
	@Override
	public PublicacionResponseDTO getAllPublicaciones(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);

		// get content for page object
		List<Publicacion> listaDePublicaciones = publicaciones.getContent();

		List<PublicacionRequestDTO> contenido = listaDePublicaciones.stream()
				.map(publicacion -> mapPublicacionToPublicacionRequestDTO(publicacion)).collect(Collectors.toList());

		PublicacionResponseDTO pubResp = new PublicacionResponseDTO();
		pubResp.setContenido(contenido);
		pubResp.setNumeroPagina(publicaciones.getNumber());
		pubResp.setMedidaPagina(publicaciones.getSize());
		pubResp.setTotalElementos(publicaciones.getTotalElements());
		pubResp.setTotalPaginas(publicaciones.getTotalPages());
		pubResp.setUltima(publicaciones.isLast());

		return pubResp;
	}

	/**
	 * 
	 */
	@Override
	public Publicacion getOneById(long id) {

		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Invalid id"));

		return publicacion;
	}

	/**
	 * 
	 */
	@Override
	public PublicacionRequestDTO updatePublicacion(PublicacionRequestDTO publicacionRequestDTO, long id) {

		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

		publicacion.setTitulo(publicacionRequestDTO.getTitulo());
		publicacion.setDescripcion(publicacionRequestDTO.getDescripcion());
		publicacion.setContenido(publicacionRequestDTO.getContenido());

		Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
		return mapPublicacionToPublicacionRequestDTO(publicacionActualizada);
	}

	/**
	 * 
	 */
	@Override
	public void deletePublicacion(long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		publicacionRepository.delete(publicacion);
	}

	/**
	 * Convierte entidad a DTO
	 * 
	 * @param publicacion
	 * @return
	 */
	private PublicacionRequestDTO mapPublicacionToPublicacionRequestDTO(Publicacion publicacion) {
		PublicacionRequestDTO publicacionDTO = modelMapper.map(publicacion, PublicacionRequestDTO.class);
		return publicacionDTO;
	}

	/**
	 * Convierte de DTO a Entidad
	 * 
	 * @param publicacionRequestDTO
	 * @return
	 */
	private Publicacion mapPublicacionReqDTOToPublicacion(PublicacionRequestDTO publicacionRequestDTO) {
		Publicacion publicacion = modelMapper.map(publicacionRequestDTO, Publicacion.class);
		return publicacion;
	}
}
