package com.tiagodeveloper.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tiagodeveloper.controller.response.UserResponse;
import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.entity.UsuarioRepository;
import com.tiagodeveloper.enums.UserStatus;
import com.tiagodeveloper.exception.BadRequestException;
import com.tiagodeveloper.service.UsuarioService;
import com.tiagodeveloper.service.converters.UsuarioConverter;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UsuarioDTO create(UsuarioDTO usuario) {
		
		if(!Objects.isNull(usuario.getId()))
			throw new BadRequestException("Não pode cadastrar usuário com id informado!!!");
		
		var entity = usuarioRepository.save(UsuarioConverter.converter(usuario));
		
		return UsuarioConverter.converter(entity);
	}
	
	@Override
	public Page<UsuarioDTO> getAll(Pageable pageable) {
		return UsuarioConverter.converter(usuarioRepository.findAll(pageable));
	}

	@Override
	public UserResponse getByDocumento(String cpf) {
		var exists = usuarioRepository.existsByDocumento(cpf);
		
		if(exists) {
			return UserResponse.builder()
					.status(UserStatus.ABLE_TO_VOTE)
					.build();
		}
		
		return UserResponse.builder()
				.status(UserStatus.UNABLE_TO_VOTE)
				.build();
	}

}
