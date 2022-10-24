package com.tiagodeveloper.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tiagodeveloper.controller.response.UserResponse;
import com.tiagodeveloper.dto.UsuarioDTO;

public interface UsuarioService {

	public UsuarioDTO create(UsuarioDTO usuario);
	
	public Page<UsuarioDTO> getAll(Pageable pageable);
	
	public UserResponse getByDocumento(String cpf);
}
