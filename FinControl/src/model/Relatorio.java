package model;

import java.util.List;

public class Relatorio {

	private List<Transacao> transacoes;

	public Relatorio(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	// Metódo que calcula total de receitas no periodo do relatorio
	public double calcularTotalReceitas() {
		double total = 0;

		for (Transacao t : transacoes) {
			if (t instanceof Receita) {
				total += t.getValor();
			}
		}
		return total;
	}

	// Metódo que calcula total de despesas no periodo do relatorio
	public double calcularTotalDespesas() {
		double total = 0;

		for (Transacao t : transacoes) {
			if (t instanceof Despesa) {
				total += t.getValor();
			}
		}

		return total;
	}

	// Método que calcula o saldo final no periodo do relatorio
	public double calcularSaldoFinal() {
		double saldo = 0;

		for (Transacao t : transacoes) {
			saldo += t.calcularValor();
		}
		return saldo;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}
}