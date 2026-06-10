package model;

import java.sql.Date;

import enums.StatusPagamento;
import enums.TipoTransacao;

public class Despesa extends Transacao {

	private StatusPagamento statusPagamento;

	public Despesa(int id, 
			String descricao, 
			double valor, 
			Date data, 
			Usuario usuario, 
			Conta conta,
			Categoria categoria, 
			StatusPagamento statusPagamento) {

		super(id, descricao, valor, data, TipoTransacao.DESPESA, usuario, conta, categoria);

		this.setStatusPagamento(statusPagamento);
	}

	@Override
	public double calcularValor() {
		return this.valor * -1;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

}