package com.crud.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.empresa.entity.Emprestimo;
import com.crud.empresa.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@PostMapping
	public ResponseEntity<Emprestimo> realizarEmprestimo(@RequestParam Long pessoaId, @RequestParam double valor,
			@RequestParam int numeroParcelas) {
		Emprestimo emprestimo = emprestimoService.realizarEmprestimo(pessoaId, valor, numeroParcelas);
		return ResponseEntity.ok(emprestimo);
	}
}
