package com.github.sanchezih.ownblog.service;

import java.util.List;

import com.github.sanchezih.ownblog.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.ownblog.entity.Comentario;

public interface ComentarioService {

	// Save operation
	public Comentario createComentario(long publicacionId, ComentarioRequestDTO comentarioDTO);

	// Read operation
	public List<Comentario> getAllComentariosByPublicacionId(long publicacionId);

	// Read operation
	public Comentario getComentarioById(Long publicacionId, Long comentarioId);

	// Update operation
	public ComentarioRequestDTO updateComentario(Long publicacionId, Long comentarioId,
			ComentarioRequestDTO solicitudDeComentario);

	// Delete operation
	public void deleteComentario(Long publicacionId, Long comentarioId);
}
