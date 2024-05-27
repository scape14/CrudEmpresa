package com.crud.empresa.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.empresa.entity.Emprestimo;
import com.crud.empresa.entity.Pessoa;
import com.crud.empresa.repository.EmprestimoRepository;
import com.crud.empresa.repository.PessoaRepository;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Emprestimo realizarEmprestimo(Long pessoaId, double valor, int numeroParcelas) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);
		if (pessoaOptional.isPresent()) {
			Pessoa pessoa = pessoaOptional.get();
			validarEmprestimo(pessoa, valor, numeroParcelas);

			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setPessoa(pessoa);
			emprestimo.setValor(valor);
			emprestimo.setNumeroParcelas(numeroParcelas);
			emprestimo.setStatusPagamento("PENDENTE");
			emprestimo.setDataCriacao(new Date());

			return emprestimoRepository.save(emprestimo);
		}
		throw new IllegalArgumentException("Pessoa não encontrada");
	}

	private void validarEmprestimo(Pessoa pessoa, double valor, int numeroParcelas) {
		if (valor > pessoa.getValorMaximoEmprestimo()) {
			throw new IllegalArgumentException("Valor do empréstimo excede o limite máximo permitido");
		}
		if (valor / numeroParcelas < pessoa.getValorMinimoMensal()) {
			throw new IllegalArgumentException("Valor da parcela é inferior ao mínimo permitido");
		}
		if (numeroParcelas > 24) {
			throw new IllegalArgumentException("Número de parcelas excede o limite máximo permitido");
		}
		validarIdentificador(pessoa.getTipoIdentificador(), pessoa.getIdentificador());
	}

	private void validarIdentificador(String tipoIdentificador, String identificador) {
		switch (tipoIdentificador) {
		case "PF":
			if (identificador.length() != 11) {
				throw new IllegalArgumentException("CPF inválido");
			}
			break;
		case "PJ":
			if (identificador.length() != 14) {
				throw new IllegalArgumentException("CNPJ inválido");
			}
			break;
		case "EU":
			if (identificador.length() != 8 || (Character.getNumericValue(identificador.charAt(0))
					+ Character.getNumericValue(identificador.charAt(7)) != 9)) {
				throw new IllegalArgumentException("Identificador de Estudante Universitário inválido");
			}
			break;
		case "AP":
			if (identificador.length() != 10
					|| identificador.chars().filter(ch -> ch == identificador.charAt(9)).count() > 1) {
				throw new IllegalArgumentException("Identificador de Aposentado inválido");
			}
			break;
		default:
			throw new IllegalArgumentException("Tipo de identificador inválido");
		}
	}
}
