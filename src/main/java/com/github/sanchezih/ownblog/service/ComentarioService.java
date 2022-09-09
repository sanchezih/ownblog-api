package com.github.sanchezih.ownblog.service;

import java.util.List;

import com.github.sanchezih.ownblog.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.ownblog.entity.Comentario;

public interface ComentarioService {

	public Comentario addComentario(long publicacionId, ComentarioRequestDTO comentarioDTO);

	public List<ComentarioRequestDTO> obtenerComentariosPorPublicacionId(long publicacionId);

	public Comentario getComentarioById(Long publicacionId, Long comentarioId);

	public ComentarioRequestDTO updateComentario(Long publicacionId, Long comentarioId,
			ComentarioRequestDTO solicitudDeComentario);

	public void deleteComentario(Long publicacionId, Long comentarioId);
}
