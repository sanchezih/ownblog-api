package com.github.sanchezih.ownblog.service;

import com.github.sanchezih.ownblog.dto.PublicacionReqDTO;
import com.github.sanchezih.ownblog.dto.PublicacionResDTO;

public interface PublicacionService {

	public PublicacionReqDTO createPublicacion(PublicacionReqDTO publicacionRequestDTO);
	
	public PublicacionResDTO getAllPublicaciones(int numeroDePagina,int medidaDePagina,String ordenarPor,String sortDir);
	
	public PublicacionReqDTO obtenerPublicacionPorId(long id);
	
	public PublicacionReqDTO actualizarPublicacion(PublicacionReqDTO publicacionRequestDTO,long id);
	
	public void eliminarPublicacion(long id);
}
