package com.github.sanchezih.ownblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.sanchezih.ownblog.dto.request.PublicacionRequestDTO;
import com.github.sanchezih.ownblog.entity.Publicacion;

public interface PublicacionService {

	public Publicacion create(PublicacionRequestDTO publicacionRequestDTO);

	public Publicacion getOne(Long id);

	public Page<Publicacion> getAll(Pageable pageable);

	public Publicacion update(PublicacionRequestDTO publicacionRequestDTO, Long id);

	public void delete(Long id);
}
