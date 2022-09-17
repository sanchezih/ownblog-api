package com.github.sanchezih.ownblog.service;

import com.github.sanchezih.ownblog.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.dto.response.PublicacionResponseDTO;
import com.github.sanchezih.ownblog.entity.Publicacion;

public interface PublicacionService {

	public PublicacionRequestDTO createPublicacion(PublicacionRequestDTO publicacionRequestDTO);

	public PublicacionResponseDTO getAllPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor,
			String sortDir);

	public Publicacion getOneById(long id);

	public PublicacionRequestDTO updatePublicacion(PublicacionRequestDTO publicacionRequestDTO, long id);

	public void deletePublicacion(long id);
}
