package com.crud.empresa.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.crud.empresa.entity.Emprestimo;
import com.crud.empresa.entity.Pessoa;
import com.crud.empresa.service.EmprestimoService;

@WebMvcTest(EmprestimoController.class)
public class EmprestimoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmprestimoService emprestimoService;

	private Emprestimo emprestimo;

	@BeforeEach
	public void setUp() {
		Pessoa pessoa = mock(Pessoa.class);
		when(pessoa.getId()).thenReturn(2L);
		emprestimo = new Emprestimo();
		emprestimo.setId(2L);
		emprestimo.setPessoa(pessoa);
		emprestimo.setValor(100000.0);
		emprestimo.setNumeroParcelas(12);
	}

	@Test
	public void testRealizarEmprestimo() throws Exception {
		when(emprestimoService.realizarEmprestimo(2L, 100000.0, 12)).thenReturn(emprestimo);

		mockMvc.perform(post("/emprestimos").param("pessoaId", "2").param("valor", "100000")
				.param("numeroParcelas", "12").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(2)).andExpect(jsonPath("$.pessoa.id").value(2))
				.andExpect(jsonPath("$.valor").value(100000.0)).andExpect(jsonPath("$.numeroParcelas").value(12));
	}
}
