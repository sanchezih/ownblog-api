package com.github.sanchezih.ownblog.service;

import java.util.List;
import com.github.sanchezih.ownblog.dto.request.ComentarioRequestDTO;
import com.github.sanchezih.ownblog.entity.Comentario;

public interface ComentarioService {
	public Comentario create(long publicacionId, ComentarioRequestDTO comentarioDTO);

	public List<Comentario> getAllComentariosByPublicacionId(Long publicacionId);

	public Comentario getOne(Long publicacionId, Long comentarioId);

	public ComentarioRequestDTO updateComentario(Long publicacionId, Long comentarioId,
			ComentarioRequestDTO solicitudDeComentario);

	public void delete(Long publicacionId, Long comentarioId);
}
