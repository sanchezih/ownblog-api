package com.github.sanchezih.ownblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sanchezih.ownblog.entity.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	public List<Comentario> findByPublicacionId(long publicacionId);
}
