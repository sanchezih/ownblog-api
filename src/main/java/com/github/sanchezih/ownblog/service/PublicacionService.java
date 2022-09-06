package com.github.sanchezih.ownblog.service;

import com.github.sanchezih.ownblog.dto.request.PublicacionReqDTO;
import com.github.sanchezih.ownblog.dto.response.PublicacionResDTO;
import com.github.sanchezih.ownblog.entity.Publicacion;

public interface PublicacionService {

	public PublicacionReqDTO addPublicacion(PublicacionReqDTO publicacionRequestDTO);

	public PublicacionResDTO getAllPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor,
			String sortDir);

	public Publicacion getOneById(long id);

	public PublicacionReqDTO updatePublicacion(PublicacionReqDTO publicacionRequestDTO, long id);

	public void deletePublicacion(long id);
}
