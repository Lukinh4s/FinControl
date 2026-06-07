package dao;

import java.sql.*;

import model.Usuario;

public class UsuarioDAO {

	private BDconexao bd;

	public UsuarioDAO() {
		this.bd = new BDconexao();
	}

	// Cadastro de usuario no banco de dados
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

			return Wfalse;
		}
	}
	
	//Busca usuario por id

}
