package model;

import enums.TipoConta;

public class Conta {

	private int id;
	private String nome;
	private double saldo;
	private TipoConta tipoConta;

	public Conta(int id, String nome, double saldo, TipoConta tipoConta) {
		this.id = id;
		this.nome = nome;
		this.saldo = saldo;
		this.tipoConta = tipoConta;
	}

	// Método para adicionar saldo
	public void adicionarSaldo(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor deve ser maior que zero!");
		}
		this.saldo += valor;
	}

	// Método para remover saldo
	public void removerSaldo(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor deve ser maior que zero!");
		}
		if (valor > this.saldo) {
			throw new IllegalArgumentException("Saldo insuficiente");
		}
		this.saldo -= valor;
	}

	// Método para consultar saldo atual
	public double consultaSaldo() {
		return this.saldo;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
}
