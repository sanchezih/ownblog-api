package com.github.sanchezih.ownblog.service;

import com.github.sanchezih.ownblog.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.dto.response.PublicacionResponseDTO;
import com.github.sanchezih.ownblog.entity.Publicacion;

public interface PublicacionService {

	public Publicacion create(PublicacionRequestDTO publicacionRequestDTO);

	public Publicacion getOne(Long id);

	public PublicacionResponseDTO getAll(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

	public Publicacion update(PublicacionRequestDTO publicacionRequestDTO, Long id);

	public void delete(Long id);
}
