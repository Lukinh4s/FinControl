package model;

import java.sql.Date;
import enums.TipoTransacao;

public abstract class Transacao {

	protected int id;
	protected String descricao;
	protected double valor;
	protected Date data;
	protected TipoTransacao tipoTransacao;
	protected Usuario usuario;
	protected Conta conta;
	protected Categoria categoria;

	public Transacao(int id, String descricao, double valor, Date data, TipoTransacao tipoTransacao, Usuario usuario,
			Conta conta, Categoria categoria) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.tipoTransacao = tipoTransacao;
		this.usuario = usuario;
		this.conta = conta;
		this.categoria = categoria;
	}
	
    //Método Abstrato que vai ser herdado nas classes filhas receita e despesa
	public abstract double calcularValor();

	public int getId() {
		return id;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	

}
