package com.tiagodeveloper.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.entity.UsuarioRepository;
import com.tiagodeveloper.exception.BadRequestException;
import com.tiagodeveloper.exception.NotFoundException;
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
	public UsuarioDTO getById(Integer id) {
		var optionalEntity = usuarioRepository.findById(id);
		
		var entity = optionalEntity.orElseThrow(() -> new NotFoundException("Not found!!"));
		
		return UsuarioConverter.converter(entity);
	}

}
