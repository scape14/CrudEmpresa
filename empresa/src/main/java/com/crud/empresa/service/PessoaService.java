package com.crud.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.empresa.entity.Pessoa;
import com.crud.empresa.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

	public Optional<Pessoa> buscarPessoaPorId(Long id) {
		return pessoaRepository.findById(id);
	}

	public Pessoa criarPessoa(Pessoa pessoa) {
        setTipoIdentificadorEValores(pessoa);
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            pessoa.setNome(pessoaAtualizada.getNome());
            pessoa.setIdentificador(pessoaAtualizada.getIdentificador());
            pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
            setTipoIdentificadorEValores(pessoa);
            return pessoaRepository.save(pessoa);
        }
        return null;
    }
	
	public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    private void setTipoIdentificadorEValores(Pessoa pessoa) {
        String identificador = pessoa.getIdentificador();
        switch (identificador.length()) {
            case 11:
                pessoa.setTipoIdentificador("PF");
                pessoa.setValorMinimoMensal(300.00);
                pessoa.setValorMaximoEmprestimo(10000.00);
                break;
            case 14:
                pessoa.setTipoIdentificador("PJ");
                pessoa.setValorMinimoMensal(1000.00);
                pessoa.setValorMaximoEmprestimo(100000.00);
                break;
            case 8:
                pessoa.setTipoIdentificador("EU");
                pessoa.setValorMinimoMensal(100.00);
                pessoa.setValorMaximoEmprestimo(10000.00);
                break;
            case 10:
                pessoa.setTipoIdentificador("AP");
                pessoa.setValorMinimoMensal(400.00);
                pessoa.setValorMaximoEmprestimo(25000.00);
                break;
            default:
                throw new IllegalArgumentException("Identificador inválido");
        }
    }
}
