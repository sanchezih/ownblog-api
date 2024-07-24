package com.github.sanchezih.ownblog.service;

import java.util.List;

import com.github.sanchezih.ownblog.dto.req.ComentarioRequestDTO;
import com.github.sanchezih.ownblog.entity.Comentario;

public interface ComentarioService {

	public Comentario create(long publicacionId, ComentarioRequestDTO comentarioDTO);

	// Read operation
	public List<Comentario> getAllComentariosByPublicacionId(long publicacionId);

	// Read operation
	public Comentario getOne(Long publicacionId, Long comentarioId);

	// Update operation
	public ComentarioRequestDTO updateComentario(Long publicacionId, Long comentarioId,
			ComentarioRequestDTO solicitudDeComentario);

	// Delete operation
	public void delete(Long publicacionId, Long comentarioId);
}
