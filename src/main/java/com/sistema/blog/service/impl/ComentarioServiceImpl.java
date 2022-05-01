package com.sistema.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.entity.Comentario;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.excepciones.BlogAppException;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repository.ComentarioRepository;
import com.sistema.blog.repository.PublicacionRepository;
import com.sistema.blog.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private PublicacionRepository publicacionRepository;

	@Override
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {

		Comentario comentario = mapearEntidad(comentarioDTO);
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		comentario.setPublicacion(publicacion);
		Comentario nuevoComentario = comentarioRepository.save(comentario);
		return mapearDTO(nuevoComentario);
	}

	@Override
	public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId) {

		List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
		return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
	}

	@Override
	public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId) {

		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}

		return mapearDTO(comentario);
	}

	@Override
	public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId,
			ComentarioDTO solicitudDeComentario) {

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
		return mapearDTO(comentarioActualizado);
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

	private ComentarioDTO mapearDTO(Comentario comentario) {
		ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
		return comentarioDTO;
	}

	private Comentario mapearEntidad(ComentarioDTO comentarioDTO) {
		Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
		return comentario;
	}
}
