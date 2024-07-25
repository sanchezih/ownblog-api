package com.github.sanchezih.ownblog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sanchezih.ownblog.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.ownblog.entity.Comentario;
import com.github.sanchezih.ownblog.entity.Publicacion;
import com.github.sanchezih.ownblog.exceptions.custom.BadRequestException;
import com.github.sanchezih.ownblog.exceptions.custom.ResourceNotFoundException;
import com.github.sanchezih.ownblog.repository.ComentarioRepository;
import com.github.sanchezih.ownblog.repository.PublicacionRepository;
import com.github.sanchezih.ownblog.service.ComentarioService;
import com.github.sanchezih.ownblog.service.PublicacionService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	private static final String COMENTARIO_NO_PERTENECE = "El comentario no pertenece a la publicacion";

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private PublicacionRepository publicacionRepository;

	@Autowired
	private PublicacionService publicacionService;

	@Override
	public Comentario create(long publicacionId, ComentarioRequestDTO comentarioRequestDTO) {

		Publicacion publicacion = publicacionService.getOne(publicacionId);

		Comentario comentario = mapComentarioRequestDTOToComentario(comentarioRequestDTO);
		comentario.setPublicacion(publicacion);

		Comentario comentarioGuardado = comentarioRepository.save(comentario);

		return comentarioGuardado;
	}

	/**
	 * 
	 */
	@Override
	public List<Comentario> getAllComentariosByPublicacionId(long publicacionId) {
		// Busco si la publicacion existe
		publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		return comentarioRepository.findByPublicacionId(publicacionId);
	}

	@Override
	public Comentario getOne(Long publicacionId, Long comentarioId) {
		// Busco si la publicacion existe
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		// Busco si el comentario existe
		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		// Busco si el comentario pertenece a la publicacion
		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BadRequestException(COMENTARIO_NO_PERTENECE);
		}

		return comentario;
	}

	@Override
	public ComentarioRequestDTO updateComentario(Long publicacionId, Long comentarioId,
			ComentarioRequestDTO solicitudDeComentario) {
		// Busco si la publicacion existe
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		// Busco si el comentario existe
		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		// Busco si el comentario pertenece a la publicacion
		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BadRequestException(COMENTARIO_NO_PERTENECE);
		}

		comentario.setNombre(solicitudDeComentario.getNombre());
		comentario.setEmail(solicitudDeComentario.getEmail());
		comentario.setCuerpo(solicitudDeComentario.getCuerpo());

		Comentario comentarioActualizado = comentarioRepository.save(comentario);
		return mapComentarioToComentarioDTO(comentarioActualizado);
	}

	@Override
	public void delete(Long publicacionId, Long comentarioId) {
		// Busco si la publicacion existe
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		// Busco si el comentario existe
		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		// Busco si el comentario pertenece a la publicacion
		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BadRequestException(COMENTARIO_NO_PERTENECE);
		}

		comentarioRepository.delete(comentario);

	}

	/**
	 * 
	 * @param comentarioRequestDTO
	 * @return
	 */
	private Comentario mapComentarioRequestDTOToComentario(ComentarioRequestDTO comentarioRequestDTO) {
		Comentario comentario = modelMapper.map(comentarioRequestDTO, Comentario.class);
		return comentario;
	}

	/**
	 * 
	 * @param comentario
	 * @return
	 */
	private ComentarioRequestDTO mapComentarioToComentarioDTO(Comentario comentario) {
		ComentarioRequestDTO comentarioDTO = modelMapper.map(comentario, ComentarioRequestDTO.class);
		return comentarioDTO;
	}

}
