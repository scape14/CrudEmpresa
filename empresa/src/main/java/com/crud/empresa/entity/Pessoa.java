package com.crud.empresa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "IDENTIFICADOR", nullable = false)
	private String identificador;
	
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private String dataNascimento;
	
	@Column(name = "TIPO_IDENTIFICADOR", nullable = false)
	private String tipoIdentificador;
	
	@Column(name = "VALOR_MIN_MENSAL", nullable = false)
	private double valorMinimoMensal;
	
	@Column(name = "VALOR_MAX_EMPRESTIMO", nullable = false)
	private double valorMaximoEmprestimo;

	public Pessoa() {
		super();
	}

	public Pessoa(Long id, String nome, String identificador, String dataNascimento, String tipoIdentificador,
			double valorMinimoMensal, double valorMaximoEmprestimo) {
		super();
		this.id = id;
		this.nome = nome;
		this.identificador = identificador;
		this.dataNascimento = dataNascimento;
		this.tipoIdentificador = tipoIdentificador;
		this.valorMinimoMensal = valorMinimoMensal;
		this.valorMaximoEmprestimo = valorMaximoEmprestimo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(String tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	public double getValorMinimoMensal() {
		return valorMinimoMensal;
	}

	public void setValorMinimoMensal(double valorMinimoMensal) {
		this.valorMinimoMensal = valorMinimoMensal;
	}

	public double getValorMaximoEmprestimo() {
		return valorMaximoEmprestimo;
	}

	public void setValorMaximoEmprestimo(double valorMaximoEmprestimo) {
		this.valorMaximoEmprestimo = valorMaximoEmprestimo;
	}

}
