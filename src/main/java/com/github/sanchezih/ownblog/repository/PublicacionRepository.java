package com.github.sanchezih.ownblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sanchezih.ownblog.entity.Publicacion;

public interface PublicacionRepository extends  JpaRepository<Publicacion, Long>{

}
