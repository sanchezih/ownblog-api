package com.github.sanchezih.ownblog.dto.response;

import java.util.List;

import com.github.sanchezih.ownblog.dto.request.PublicacionRequestDTO;

public class PublicacionResponseDTO {

	private List<PublicacionRequestDTO> contenido;
	private int numeroPagina;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;

	/*----------------------------------------------------------------------------*/

	public PublicacionResponseDTO() {
		super();
	}

	/*----------------------------------------------------------------------------*/

	public List<PublicacionRequestDTO> getContenido() {
		return contenido;
	}

	public void setContenido(List<PublicacionRequestDTO> contenido) {
		this.contenido = contenido;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public int getMedidaPagina() {
		return medidaPagina;
	}

	public void setMedidaPagina(int medidaPagina) {
		this.medidaPagina = medidaPagina;
	}

	public long getTotalElementos() {
		return totalElementos;
	}

	public void setTotalElementos(long totalElementos) {
		this.totalElementos = totalElementos;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public boolean isUltima() {
		return ultima;
	}

	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}

}
