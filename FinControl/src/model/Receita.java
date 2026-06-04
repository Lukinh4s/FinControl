package model;

import java.time.LocalDate;

import enums.TipoTransacao;

public class Receita extends Transacao {

	private String fonte;

	public Receita(int id, String descricao, double valor, LocalDate data, Usuario usuario, Conta conta,
			Categoria categoria, String fonte) {
		super(id, descricao, valor, data, TipoTransacao.RECEITA, usuario, conta, categoria);
		this.setFonte(fonte);
	}

	// Imprementando o método abstrato da classe Transacao
	@Override
	public double calcularValor() {
		return this.valor;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

}
