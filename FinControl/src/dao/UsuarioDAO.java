package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {

	private BDconexao bd;

	public UsuarioDAO() {
		this.bd = new BDconexao();
	}

	// Cadastrar usuário
	public boolean cadastrar(Usuario usuario) {

		if (!bd.connect()) {
			return false;
		}

		String sql = "INSERT INTO usuarios (nome, email, senha) " + "VALUES (?, ?, ?)";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao cadastrar usuário: " + erro.getMessage());

			return false;
		}
	}

	// Buscar usuário pelo ID
	public Usuario buscarPorId(int id) {

		if (!bd.connect()) {
			return null;
		}

		String sql = "SELECT * FROM usuarios WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("senha"));

				rs.close();
				stmt.close();
				bd.close();

				return usuario;
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {

			System.out.println("Erro ao buscar usuário: " + erro.getMessage());
		}

		return null;
	}

	// Buscar usuário pelo email
	public Usuario buscarPorEmail(String email) {

		if (!bd.connect()) {
			return null;
		}

		String sql = "SELECT * FROM usuarios WHERE email = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("senha"));

				rs.close();
				stmt.close();
				bd.close();

				return usuario;
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {

			System.out.println("Erro ao buscar email: " + erro.getMessage());
		}

		return null;
	}

	// Atualizar usuário

	public boolean atualizar(Usuario usuario) {

		if (!bd.connect()) {
			return false;
		}

		String sql = "UPDATE usuarios " + "SET nome = ?, email = ?, senha = ? " + "WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setInt(4, usuario.getId());

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao atualizar usuário: " + erro.getMessage());

			return false;
		}
	}

	// Excluir usuário
	public boolean excluir(int id) {

		if (!bd.connect()) {
			return false;
		}

		String sql = "DELETE FROM usuarios WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao excluir usuário: " + erro.getMessage());

			return false;
		}
	}

	// Listar todos os usuários
	public List<Usuario> listar() {

		List<Usuario> usuarios = new ArrayList<>();

		if (!bd.connect()) {
			return usuarios;
		}

		String sql = "SELECT * FROM usuarios";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("senha"));

				usuarios.add(usuario);
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {

			System.out.println("Erro ao listar usuários: " + erro.getMessage());
		}

		return usuarios;
	}
}