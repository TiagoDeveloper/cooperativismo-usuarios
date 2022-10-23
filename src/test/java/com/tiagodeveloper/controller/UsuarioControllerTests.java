package com.tiagodeveloper.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagodeveloper.dto.UsuarioDTO;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UsuarioControllerTests {

	ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	@SqlGroup(
		value = {
			@Sql(
				executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, 
				scripts = "classpath:test/insert.sql"
			),
			@Sql(
				executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, 
				scripts = "classpath:test/delete.sql"
			)
		}
	)
	void getAllTest() throws Exception {
		mockMvc.perform(get("/usuario")
	            .contentType("application/json"))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.empty",equalTo(false)))
	            .andExpect(jsonPath("$.numberOfElements",equalTo(3)))
	            .andExpect(jsonPath("$.content[0].documento",equalTo("97794397065")));
		
	}
	
	@Test
	void createTest() throws Exception {
		
		var expectedRecord = UsuarioDTO.builder().nome("Raimundo").documento("08774774000").build();
		var actualRecord = om.readValue(mockMvc.perform(post("/usuario")
	            .contentType("application/json")
	            .content(om.writeValueAsString(expectedRecord)))
	            .andDo(print())
	            .andExpect(jsonPath("$.id", greaterThan(0)))
	            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);

	    assertEquals(expectedRecord.getDocumento(), actualRecord.getDocumento());
		
	}
	
	
	@Test
	@SqlGroup(
		value = {
			@Sql(
				executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, 
				scripts = "classpath:test/insert.sql"
			),
			@Sql(
				executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, 
				scripts = "classpath:test/delete.sql"
			)
		}
	)
	void getByIdTest() throws Exception {
		
		var expectedRecord = UsuarioDTO.builder().documento("97794397065").build();
		
		var actualRecord = om.readValue(mockMvc.perform(get("/usuario/111")
	            .contentType("application/json"))
	            .andDo(print())
	            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);

	    assertEquals(expectedRecord.getDocumento(), actualRecord.getDocumento());
		
	}

}
