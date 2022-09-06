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

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private PublicacionRepository publicacionRepository;

	@Override
	public ComentarioRequestDTO crearComentario(long publicacionId, ComentarioRequestDTO comentarioDTO) {

		Comentario comentario = mapearEntidad(comentarioDTO);
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		comentario.setPublicacion(publicacion);
		Comentario nuevoComentario = comentarioRepository.save(comentario);
		return mapComentarioToComentarioDTO(nuevoComentario);
	}

	@Override
	public List<ComentarioRequestDTO> obtenerComentariosPorPublicacionId(long publicacionId) {

		List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
		return comentarios.stream().map(comentario -> mapComentarioToComentarioDTO(comentario)).collect(Collectors.toList());
	}

	@Override
	public ComentarioRequestDTO getComentarioById(Long publicacionId, Long comentarioId) {

		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}

		return mapComentarioToComentarioDTO(comentario);
	}

	@Override
	public ComentarioRequestDTO actualizarComentario(Long publicacionId, Long comentarioId,
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

	@Override
	public void eliminarComentario(Long publicacionId, Long comentarioId) {

		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}

		comentarioRepository.delete(comentario);
	}

	private ComentarioRequestDTO mapComentarioToComentarioDTO(Comentario comentario) {
		ComentarioRequestDTO comentarioDTO = modelMapper.map(comentario, ComentarioRequestDTO.class);
		return comentarioDTO;
	}

	private Comentario mapearEntidad(ComentarioRequestDTO comentarioDTO) {
		Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
		return comentario;
	}
}
