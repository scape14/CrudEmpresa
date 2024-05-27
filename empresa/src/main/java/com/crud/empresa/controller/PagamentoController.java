package com.crud.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.empresa.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/{emprestimoId}")
    public ResponseEntity<Void> pagarEmprestimo(@PathVariable Long emprestimoId) {
        pagamentoService.pagarEmprestimo(emprestimoId);
        return ResponseEntity.ok().build();
    }
}
