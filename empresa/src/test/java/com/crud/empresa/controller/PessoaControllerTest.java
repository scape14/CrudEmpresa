package com.crud.empresa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crud.empresa.entity.Pessoa;
import com.crud.empresa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PessoaControllerTest {
	@Mock
	private PessoaService pessoaService;

	@InjectMocks
	private PessoaController pessoaController;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
	}

	@Test
	public void testListarPessoas() throws Exception {
		Pessoa pessoa1 = new Pessoa(1L, "João", null, null, null, 0, 0);
		Pessoa pessoa2 = new Pessoa(2L, "Maria", null, null, null, 0, 0);

		when(pessoaService.listarPessoas()).thenReturn(Arrays.asList(pessoa1, pessoa2));

		mockMvc.perform(get("/pessoas")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].nome").value("João")).andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].nome").value("Maria"));

		verify(pessoaService, times(1)).listarPessoas();
	}

	@Test
    void testCriarPessoa() {
        Pessoa novaPessoa = new Pessoa();
        when(pessoaService.criarPessoa(any(Pessoa.class))).thenReturn(novaPessoa);

        ResponseEntity<Pessoa> responseEntity = pessoaController.criarPessoa(novaPessoa);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(novaPessoa, responseEntity.getBody());
    }

	@Test
	public void testBuscarPessoaPorId() throws Exception {
		Pessoa pessoa = new Pessoa(1L, "Carlos", null, null, null, 0, 0);

		when(pessoaService.buscarPessoaPorId(1L)).thenReturn(Optional.of(pessoa));

		mockMvc.perform(get("/pessoas/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1L));

		verify(pessoaService, times(1)).buscarPessoaPorId(1L);
	}

	@Test
	public void testBuscarPessoaPorIdNotFound() throws Exception {
		when(pessoaService.buscarPessoaPorId(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/pessoas/1")).andExpect(status().isNotFound());

		verify(pessoaService, times(1)).buscarPessoaPorId(1L);
	}

	@Test
	public void testAtualizarPessoa() throws Exception {
		Pessoa pessoa = new Pessoa(1L, "Roberto", null, null, null, 0, 0);
		Pessoa pessoaAtualizada = new Pessoa(1L, "Roberto Atualizado", null, null, null, 0, 0);

		when(pessoaService.atualizarPessoa(eq(1L), any(Pessoa.class))).thenReturn(pessoaAtualizada);

		mockMvc.perform(put("/pessoas/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pessoa))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L)).andExpect(jsonPath("$.nome").value("Roberto Atualizado"));
	}

	@Test
	public void testAtualizarPessoaNotFound() throws Exception {
		Pessoa pessoa = mock(Pessoa.class);

		when(pessoaService.atualizarPessoa(eq(1L), any(Pessoa.class))).thenReturn(null);

		mockMvc.perform(put("/pessoas/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pessoa))).andExpect(status().isNotFound());

		verify(pessoaService, times(1)).atualizarPessoa(eq(1L), any(Pessoa.class));
	}

	@Test
	public void testDeletarPessoa() throws Exception {
		doNothing().when(pessoaService).deletarPessoa(1L);

		mockMvc.perform(delete("/pessoas/1")).andExpect(status().isNoContent());

		verify(pessoaService, times(1)).deletarPessoa(1L);
	}
}
