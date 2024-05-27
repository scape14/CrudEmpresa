package com.crud.empresa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.empresa.entity.Emprestimo;
import com.crud.empresa.repository.EmprestimoRepository;

@Service
public class PagamentoService {
	
	@Autowired
    private EmprestimoRepository emprestimoRepository;

    public void pagarEmprestimo(Long emprestimoId) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(emprestimoId);
        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();
            if ("PENDENTE".equals(emprestimo.getStatusPagamento())) {
                emprestimo.setStatusPagamento("PAGO");
                emprestimoRepository.save(emprestimo);
            } else {
                throw new IllegalStateException("Empréstimo já está pago ou possui status inválido");
            }
        } else {
            throw new IllegalArgumentException("Empréstimo não encontrado");
        }
    }
}
