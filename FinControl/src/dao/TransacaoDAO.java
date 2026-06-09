package dao;

import java.sql.*;
import java.util.Date;
import java.util.List;

import model.Despesa;
import model.Transacao;

public class TransacaoDAO {
	
	private BDconexao bd;

	public TransacaoDAO() {
		this.bd = new BDconexao();
	}

	// Cadastra uma transação no BD
    public boolean cadastrar(Transacao transacao, int idUsuario, int idConta, int idCategoria) {
    	if (!bd.connect()) {
			return false;
		}
    	
		String sql = "INSERT INTO transacoes " +
				"(descricao, valor, data_transacao, tipo_transacao, status_pagamento, usuario_id, conta_id, categoria_id) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);
			
			    
			stmt.setString(1, transacao.getDescricao());
			stmt.setDouble(2, transacao.getValor());
			
			java.sql.Date dataSql = new java.sql.Date(
					transacao.getData().getTime()
					);
			stmt.setDate(3, dataSql);
			
			stmt.setString(4, transacao.getTipoTransacao().name());
			
			if(transacao instanceof Despesa) {
				Despesa despesa = (Despesa) transacao;
				
				stmt.setString(5, despesa.getStatusPagamento().name());
			} else {
				stmt.setNull(5, Types.VARCHAR);
			}
			
			stmt.setInt(6, idUsuario);
			stmt.setInt(7, idConta);
			stmt.setInt(8, idCategoria);
		
			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao cadastrar transacao: " + erro.getMessage());

			return false;
		}
    }

    public boolean atualizar(Transacao transacao) {
        // UPDATE
    }

    public boolean excluir(int id) {
        // DELETE
    }

    public Transacao buscarPorId(int id) {
        // SELECT
        return null;
    }

    public List<Transacao> listarPorUsuario(int idUsuario) {
        // SELECT
        return null;
    }

    public List<Transacao> listarPorPeriodo(
            int idUsuario,
            Date dataInicio,
            Date dataFim) {

        // SELECT BETWEEN

        return null;
    }

    public List<Transacao> listarPorCategoria(
            int idUsuario,
            int idCategoria) {

        // SELECT categoria

        return null;
    }

}