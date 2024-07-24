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

import com.github.sanchezih.ownblog.dto.req.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.dto.res.PublicacionResponseDTO;
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
	public Publicacion create(PublicacionRequestDTO publicacionRequestDTO) {
		Publicacion publicacion = mapPublicacionRequestDTOToPublicacion(publicacionRequestDTO);
		publicacionRepository.save(publicacion);
		return publicacion;
	}

	/**
	 * 
	 */
	@Override
	public Publicacion getOne(Long id) {

		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("id invalido"));

		return publicacion;
	}

	/**
	 * 
	 */
	@Override
	public PublicacionResponseDTO getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
		List<Publicacion> listaDePublicaciones = publicaciones.getContent();

		// Si bien aca habria que devolver objetos de tipo Publicacion, se
		// opta por devolver objetos de tipo PublicacionRequestDTO para mostrar lo
		// siguiente...
		List<PublicacionRequestDTO> contenido = listaDePublicaciones.stream()
				.map(publicacion -> mapPublicacionToPublicacionRequestDTO(publicacion)).collect(Collectors.toList());

		PublicacionResponseDTO res = new PublicacionResponseDTO();
		res.setContenido(contenido);
		res.setNumeroPagina(publicaciones.getNumber());
		res.setMedidaPagina(publicaciones.getSize());
		res.setTotalElementos(publicaciones.getTotalElements());
		res.setTotalPaginas(publicaciones.getTotalPages());
		res.setUltima(publicaciones.isLast());

		return res;
	}

	/**
	 * 
	 */
	@Override
	public Publicacion update(PublicacionRequestDTO publicacionRequestDTO, Long id) {

		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

		publicacion.setTitulo(publicacionRequestDTO.getTitulo());
		publicacion.setContenido(publicacionRequestDTO.getContenido());

		publicacionRepository.save(publicacion);
		return publicacion;
	}

	/**
	 * 
	 */
	@Override
	public void delete(Long id) {
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
	 * Convierte DTO a entidad
	 * 
	 * @param publicacionRequestDTO
	 * @return
	 */
	private Publicacion mapPublicacionRequestDTOToPublicacion(PublicacionRequestDTO publicacionRequestDTO) {
		Publicacion aeropuerto = modelMapper.map(publicacionRequestDTO, Publicacion.class);
		return aeropuerto;
	}
}
