package com.github.sanchezih.ownblog.service;

import java.util.List;

import com.github.sanchezih.ownblog.dto.request.ComentarioRequestDTO;

public interface ComentarioService {

	public ComentarioRequestDTO crearComentario(long publicacionId, ComentarioRequestDTO comentarioDTO);

	public List<ComentarioRequestDTO> obtenerComentariosPorPublicacionId(long publicacionId);

	public ComentarioRequestDTO getComentarioById(Long publicacionId, Long comentarioId);

	public ComentarioRequestDTO actualizarComentario(Long publicacionId, Long comentarioId,
			ComentarioRequestDTO solicitudDeComentario);

	public void eliminarComentario(Long publicacionId, Long comentarioId);
}
