package com.crud.empresa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.crud.empresa.service.PagamentoService;

public class PagamentoControllerTest {

	@InjectMocks
	private PagamentoController pagamentoController;

	@Mock
	private PagamentoService pagamentoService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testPagarEmprestimo() {
		Long emprestimoId = 1L;

		doNothing().when(pagamentoService).pagarEmprestimo(emprestimoId);
		ResponseEntity<Void> response = pagamentoController.pagarEmprestimo(emprestimoId);
		assertEquals(ResponseEntity.ok().build(), response);
	}
}
