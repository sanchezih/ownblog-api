package com.github.sanchezih.ownblog.dto;

import java.util.List;

public class PublicacionResponseDTO {

	private List<PublicacionRequestDTO> contenido;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;

	/*----------------------------------------------------------------------------*/

	public PublicacionResponseDTO() {
	}

	/*----------------------------------------------------------------------------*/

	public List<PublicacionRequestDTO> getContenido() {
		return contenido;
	}

	public void setContenido(List<PublicacionRequestDTO> contenido) {
		this.contenido = contenido;
	}

	public int getNumeroPagina() {
		return pageNo;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.pageNo = numeroPagina;
	}

	public int getMedidaPagina() {
		return pageSize;
	}

	public void setMedidaPagina(int medidaPagina) {
		this.pageSize = medidaPagina;
	}

	public long getTotalElementos() {
		return totalElements;
	}

	public void setTotalElementos(long totalElementos) {
		this.totalElements = totalElementos;
	}

	public int getTotalPaginas() {
		return totalPages;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPages = totalPaginas;
	}

	public boolean isUltima() {
		return last;
	}

	public void setUltima(boolean ultima) {
		this.last = ultima;
	}

}
