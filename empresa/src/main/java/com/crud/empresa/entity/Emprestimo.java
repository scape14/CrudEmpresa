package com.crud.empresa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "VALOR_EMPRESTIMO", nullable = false)
	private double valor;
	
	@Column(name = "NUMERO_PARCELAS", nullable = false)
	private int numeroParcelas;
	
	@Column(name = "STATUS_PAGAMENTO", nullable = false)
	private String statusPagamento;
	
	@Column(name = "DATA_CRIACAO", nullable = false)
	private Date dataCriacao;

	@ManyToOne
	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	private Pessoa pessoa;

	public Emprestimo() {
		super();
	}

	public Emprestimo(Long id, double valor, int numeroParcelas, String statusPagamento, Date dataCriacao,
			Pessoa pessoa) {
		super();
		this.id = id;
		this.valor = valor;
		this.numeroParcelas = numeroParcelas;
		this.statusPagamento = statusPagamento;
		this.dataCriacao = dataCriacao;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
