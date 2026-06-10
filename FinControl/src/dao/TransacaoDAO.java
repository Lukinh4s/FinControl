package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enums.StatusPagamento;
import model.Categoria;
import model.Conta;
import model.Despesa;
import model.Receita;
import model.Transacao;
import model.Usuario;

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

		String sql = "INSERT INTO transacoes "
				+ "(descricao, valor, data_transacao, tipo_transacao, status_pagamento, usuario_id, conta_id, categoria_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, transacao.getDescricao());
			stmt.setDouble(2, transacao.getValor());

			java.sql.Date dataSql = new java.sql.Date(transacao.getData().getTime());
			stmt.setDate(3, dataSql);

			stmt.setString(4, transacao.getTipoTransacao().name());

			if (transacao instanceof Despesa) {
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

	// Atualiza transacao no BD
	public boolean atualizar(Transacao transacao) {
		if (!bd.connect()) {
			return false;
		}

		String sql = "UPDATE transacoes SET descricao = ?, valor = ?, data_transacao = ?, "
				+ "tipo_transacao = ?, status_pagamento = ?, " + "usuario_id = ?, conta_id = ?, categoria_id = ? "
				+ "WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, transacao.getDescricao());
			stmt.setDouble(2, transacao.getValor());

			java.sql.Date dataSql = new java.sql.Date(transacao.getData().getTime());

			stmt.setDate(3, dataSql);
			stmt.setString(4, transacao.getTipoTransacao().name());

			if (transacao instanceof Despesa) {
				Despesa despesa = (Despesa) transacao;
				stmt.setString(5, despesa.getStatusPagamento().name());
			} else {
				stmt.setNull(5, Types.VARCHAR);
			}

			stmt.setInt(6, transacao.getUsuario().getId());
			stmt.setInt(7, transacao.getConta().getId());
			stmt.setInt(8, transacao.getCategoria().getId());
			stmt.setInt(9, transacao.getId());

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar transação: " + erro.getMessage());
			return false;
		}
	}

	// Exclui transacao no BD
	public boolean excluir(int id) {
		if (!bd.connect()) {
			return false;
		}

		String sql = "DELETE FROM transacoes WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {
			System.out.println("Erro ao excluir transação: " + erro.getMessage());
			return false;
		}
	}

	public Transacao buscarPorId(int id) {

		if (!bd.connect()) {
			return null;
		}

		String sql = "SELECT * FROM transacoes WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				Transacao transacao = montarTransacao(rs);

				rs.close();
				stmt.close();
				bd.close();

				return transacao;
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {
			System.out.println("Erro ao buscar transação: " + erro.getMessage());
		}

		return null;
	}

	// Lista transacao por usuario no BD
	public List<Transacao> listarPorUsuario(int idUsuario) {

		List<Transacao> transacoes = new ArrayList<>();

		if (!bd.connect()) {
			return transacoes;
		}

		String sql = "SELECT * FROM transacoes WHERE usuario_id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, idUsuario);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				transacoes.add(montarTransacao(rs));
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {
			System.out.println("Erro ao listar transações: " + erro.getMessage());
		}

		return transacoes;
	}

	// Lista transacao por periodo no BD
	public List<Transacao> listarPorPeriodo(int idUsuario, Date dataInicio, Date dataFim) {

		List<Transacao> transacoes = new ArrayList<>();

		if (!bd.connect()) {
			return transacoes;
		}

		String sql = "SELECT * FROM transacoes " + "WHERE usuario_id = ? " + "AND data_transacao BETWEEN ? AND ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, idUsuario);

			java.sql.Date inicioSql = new java.sql.Date(dataInicio.getTime());
			java.sql.Date fimSql = new java.sql.Date(dataFim.getTime());

			stmt.setDate(2, inicioSql);
			stmt.setDate(3, fimSql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				transacoes.add(montarTransacao(rs));
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {
			System.out.println("Erro ao listar por período: " + erro.getMessage());
		}

		return transacoes;
	}

	public List<Transacao> listarPorCategoria(int idUsuario, int idCategoria) {

		List<Transacao> transacoes = new ArrayList<>();

		if (!bd.connect()) {
			return transacoes;
		}

		String sql = "SELECT * FROM transacoes " + "WHERE usuario_id = ? " + "AND categoria_id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, idUsuario);
			stmt.setInt(2, idCategoria);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				transacoes.add(montarTransacao(rs));
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {
			System.out.println("Erro ao listar por categoria: " + erro.getMessage());
		}

		return transacoes;
	}

	// Mostra a transacao no BD
	private Transacao montarTransacao(ResultSet rs) throws SQLException {

	    UsuarioDAO usuarioDAO = new UsuarioDAO();
	    Usuario usuario = usuarioDAO.buscarPorId(
	        rs.getInt("usuario_id")
	    );

	    ContaDAO contaDAO = new ContaDAO();
	    Conta conta = contaDAO.buscarPorId(
	        rs.getInt("conta_id")
	    );

	    CategoriaDAO categoriaDAO = new CategoriaDAO();
	    Categoria categoria = categoriaDAO.buscarPorId(
	        rs.getInt("categoria_id")
	    );

	    String tipo = rs.getString("tipo_transacao");

	    if (tipo.equals("RECEITA")) {

	        return new Receita(
	            rs.getInt("id"),
	            rs.getString("descricao"),
	            rs.getDouble("valor"),
	            rs.getDate("data_transacao"),
	            usuario,
	            conta,
	            categoria,
	            null
	        );

	    } else {

	        return new Despesa(
	            rs.getInt("id"),
	            rs.getString("descricao"),
	            rs.getDouble("valor"),
	            rs.getDate("data_transacao"),
	            usuario,
	            conta,
	            categoria,
	            StatusPagamento.valueOf(
	            rs.getString("status_pagamento")
	            ));
	    }
	}

}