package com.github.sanchezih.ownblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.sanchezih.ownblog.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.ownblog.entity.Comentario;

public interface ComentarioService {
	public Comentario create(Long publicacionId, ComentarioRequestDTO comentarioDTO);

	public Page<Comentario> getAllComentariosByPublicacionId(Long publicacionId, Pageable pageable);

	public Comentario getOne(Long publicacionId, Long comentarioId);

	public ComentarioRequestDTO updateComentario(Long publicacionId, Long comentarioId,
			ComentarioRequestDTO solicitudDeComentario);

	public void delete(Long publicacionId, Long comentarioId);
}
