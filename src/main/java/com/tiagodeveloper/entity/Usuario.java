package com.tiagodeveloper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence_generator")
    @SequenceGenerator(name = "usuario_sequence_generator", sequenceName = "usuario_id_seq", initialValue = 1,allocationSize = 1)
	private Integer id;
	private String nome;
	private String documento;
	
}
