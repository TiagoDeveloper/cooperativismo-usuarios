package com.tiagodeveloper.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	
	public Optional<Usuario> findByDocumento(String documento);
	
	public Boolean existsByDocumento(String documento);
}
