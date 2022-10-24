package com.tiagodeveloper.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tiagodeveloper.controller.response.UserResponse;
import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.service.UsuarioService;


@RestController
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<UsuarioDTO>> getAll(Pageable pageable){
		return ResponseEntity.ok(usuarioService.getAll(pageable));
	}
	
	@GetMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserResponse> getBygetByDocumento(@PathVariable("cpf") String cpf){
		return ResponseEntity.ok(usuarioService.getByDocumento(cpf));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO){
		return new ResponseEntity<>(usuarioService.create(usuarioDTO), HttpStatus.CREATED);
	}
	
	
}
