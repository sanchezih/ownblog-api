package com.github.sanchezih.ownblog.service;

import com.github.sanchezih.ownblog.dto.PublicacionDTO;
import com.github.sanchezih.ownblog.dto.PublicacionRespuesta;

public interface PublicacionService {

	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina,int medidaDePagina,String ordenarPor,String sortDir);
	
	public PublicacionDTO obtenerPublicacionPorId(long id);
	
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO,long id);
	
	public void eliminarPublicacion(long id);
}
