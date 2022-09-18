package com.github.sanchezih.ownblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.sanchezih.ownblog.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.ownblog.entity.Comentario;
import com.github.sanchezih.ownblog.entity.Publicacion;
import com.github.sanchezih.ownblog.excepciones.BlogAppException;
import com.github.sanchezih.ownblog.excepciones.ResourceNotFoundException;
import com.github.sanchezih.ownblog.repository.ComentarioRepository;
import com.github.sanchezih.ownblog.repository.PublicacionRepository;
import com.github.sanchezih.ownblog.service.ComentarioService;
import com.github.sanchezih.ownblog.service.PublicacionService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private PublicacionRepository publicacionRepository;

	@Autowired
	private PublicacionService publicacionService;

	/**
	 * 
	 */
	@Override
	public Comentario createComentario(long publicacionId, ComentarioRequestDTO comentarioRequestDTO) {

		Publicacion publicacion = publicacionService.getOneById(publicacionId);

		Comentario c = mapComentarioRequestDTOToComentario(comentarioRequestDTO);
		c.setPublicacion(publicacion);

		Comentario comentarioGuardado = comentarioRepository.save(c);
		return comentarioGuardado;

	}

	/**
	 * 
	 */
	@Override
	public List<Comentario> getAllComentariosByPublicacionId(long publicacionId) {

		List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
		return comentarios;
	//	return comentarios.stream().map(comentario -> mapComentarioToComentarioDTO(comentario)).collect(Collectors.toList());
	}

	/**
	 * 
	 */
	@Override
	public Comentario getComentarioById(Long publicacionId, Long comentarioId) {

		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}

		// return mapComentarioToComentarioDTO(comentario);
		return comentario;
	}

	/**
	 * 
	 */
	@Override
	public ComentarioRequestDTO updateComentario(Long publicacionId, Long comentarioId,
			ComentarioRequestDTO solicitudDeComentario) {

		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}

		comentario.setNombre(solicitudDeComentario.getNombre());
		comentario.setEmail(solicitudDeComentario.getEmail());
		comentario.setCuerpo(solicitudDeComentario.getCuerpo());

		Comentario comentarioActualizado = comentarioRepository.save(comentario);
		return mapComentarioToComentarioDTO(comentarioActualizado);
	}

	/**
	 * 
	 */
	@Override
	public void deleteComentario(Long publicacionId, Long comentarioId) {

		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
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
