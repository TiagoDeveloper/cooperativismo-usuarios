package com.tiagodeveloper.service.converters;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.entity.Usuario;

public class UsuarioConverter {
	
	
	public static Usuario converter(UsuarioDTO dto) {
		return new Usuario(dto.getId(), dto.getNome(), dto.getDocumento());
	}
	public static UsuarioDTO converter(Usuario entity) {
		return new UsuarioDTO(entity.getId(), entity.getNome(), entity.getDocumento());
	}
	public static Page<UsuarioDTO> converter(Page<Usuario> pageEntity) {
		var listDTO = pageEntity.getContent().stream().map(entity -> 
			new UsuarioDTO(entity.getId(), entity.getNome(), entity.getDocumento())
		).collect(Collectors.toList());
		return new PageImpl<>(listDTO, pageEntity.getPageable(), pageEntity.getTotalElements());
	}
}
