package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import enums.TipoConta;
import model.Conta;

public class ContaDAO {

	private BDconexao bd;

	public ContaDAO() {
		this.bd = new BDconexao();
	}

	// Cadastra conta no BD
	public boolean cadastrar(Conta conta, int idUsuario) {
		if (!bd.connect()) {
			return false;
		}

		String sql = "INSERT INTO contas (nome, saldo, tipo_conta, usuario_id) " + "VALUES (?, ?, ?, ?)";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, conta.getNome());
			stmt.setDouble(2, conta.getSaldo());
			stmt.setString(3, conta.getTipoConta().name());
			stmt.setInt(4, idUsuario);

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao cadastrar conta: " + erro.getMessage());

			return false;
		}
	}

	// Atualiza conta no BD
	public boolean atualizar(Conta conta) {
		if (!bd.connect()) {
			return false;
		}

		String sql = "UPDATE contas " + "SET nome = ?, saldo = ?, tipo_conta = ? " + "WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, conta.getNome());
			stmt.setDouble(2, conta.getSaldo());
			stmt.setString(3, conta.getTipoConta().name());
			stmt.setInt(4, conta.getId());

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao atualizar conta: " + erro.getMessage());

			return false;
		}
	}

	// Atualiza saldo da conta no BD
	public boolean atualizarSaldo(Conta conta) {
		if (!bd.connect()) {
			return false;
		}

		String sql = "UPDATE contas " + "SET saldo = ? WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setDouble(1, conta.getSaldo());
			stmt.setInt(2, conta.getId());

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao atualizar saldo: " + erro.getMessage());

			return false;
		}
	}

	// Exclui conta no BD
	public boolean excluir(int id) {
		if (!bd.connect()) {
			return false;
		}

		String sql = "DELETE FROM contas WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao excluir conta: " + erro.getMessage());

			return false;
		}
	}

	// Busca conta por ID no BD.
	public Conta buscarPorId(int id) {

		if (!bd.connect()) {
			return null;
		}

		String sql = "SELECT * FROM contas WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				Conta conta = new Conta(rs.getInt("id"), rs.getString("nome"), rs.getDouble("saldo"),
						TipoConta.valueOf(rs.getString("tipo_conta")));

				rs.close();
				stmt.close();
				bd.close();

				return conta;
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {

			System.out.println("Erro ao buscar conta: " + erro.getMessage());
		}

		return null;
	}

	// Busca lista das contas no BD.
	// Conflito de código pelo nome da lista e do conta no BD, obs estava "conta"
	// nos dois.
	// "contas"= ArryList.
	// "conta" = Tabela do BD.
	public List<Conta> listarPorUsuario(int idUsuario) {
		List<Conta> contas = new ArrayList<>();

		if (!bd.connect()) {
			return contas;
		}

		String sql = "SELECT * FROM contas WHERE usuario_id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, idUsuario);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Conta conta = new Conta(rs.getInt("id"), rs.getString("nome"), rs.getDouble("saldo"),
						TipoConta.valueOf(rs.getString("tipo_conta")));

				contas.add(conta);
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {

			System.out.println("Erro ao listar as contas: " + erro.getMessage());
		}

		return contas;
	}

	// Busca lista das contas pelo nome
	// Ou seja quando o usuario quiser saber quais contas ele possui
	// pelo nome delas. EX: Nubank | R$ 33,00 | Despesa e mais contas da nubank ou
	// outras contas
	public List<Conta> listarPorNome(int idUsuario, String nome) {
		List<Conta> contas = new ArrayList<>();

		if (!bd.connect()) {
			return contas;
		}

		String sql = "SELECT * FROM contas WHERE usuario_id = ? AND nome LIKE ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, idUsuario);
			stmt.setString(2, "%" + nome + "%");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Conta conta = new Conta(rs.getInt("id"), rs.getString("nome"), rs.getDouble("saldo"),
						TipoConta.valueOf(rs.getString("tipo_conta")));
				contas.add(conta);
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {
			System.out.println("Erro ao listar contas por nome: " + erro.getMessage());
		}
		return contas;
	}

}
