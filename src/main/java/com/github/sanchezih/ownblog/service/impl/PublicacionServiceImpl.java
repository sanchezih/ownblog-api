package com.github.sanchezih.ownblog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.sanchezih.ownblog.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.entity.Publicacion;
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

	@Override
	public Publicacion create(PublicacionRequestDTO publicacionRequestDTO) {
		Publicacion publicacionACrear = mapPublicacionRequestDTOToPublicacion(publicacionRequestDTO);
		Publicacion publicacionCreada = publicacionRepository.save(publicacionACrear);
		return publicacionCreada;
	}

	@Override
	public Publicacion getOne(Long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		return publicacion;
	}

	@Override
	public Page<Publicacion> getAll(Pageable pageable) {
		return publicacionRepository.findAll(pageable);
	}

	@Override
	public Publicacion update(PublicacionRequestDTO publicacionRequestDTO, Long id) {
		Publicacion publicacionAActualizar = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

		publicacionAActualizar.setTitulo(publicacionRequestDTO.getTitulo());
		publicacionAActualizar.setContenido(publicacionRequestDTO.getContenido());

		Publicacion publicacionActualizada = publicacionRepository.save(publicacionAActualizar);
		return publicacionActualizada;
	}

	@Override
	public void delete(Long id) {
		Publicacion publicacionAEliminar = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		publicacionRepository.delete(publicacionAEliminar);
	}

	private Publicacion mapPublicacionRequestDTOToPublicacion(PublicacionRequestDTO publicacionRequestDTO) {
		Publicacion publicacion = modelMapper.map(publicacionRequestDTO, Publicacion.class);
		return publicacion;
	}

}
