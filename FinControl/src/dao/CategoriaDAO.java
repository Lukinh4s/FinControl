package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

public class CategoriaDAO {

	private BDconexao bd;

	public CategoriaDAO() {
		this.bd = new BDconexao();
	}

	// Cadastra categoria no BD
	public boolean cadastrar(Categoria categoria, int idUsuario) {
		if (!bd.connect()) {
			return false;
		}

		String sql = "INSERT INTO categorias (nome, usuario_id) VALUES (?, ?)";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, categoria.getNome());
			stmt.setInt(2, idUsuario);

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao cadastrar categoria: " + erro.getMessage());

			return false;
		}
	}

	// Atualiza categoria no BD
	public boolean atualizar(Categoria categoria) {
		if (!bd.connect()) {
			return false;
		}

		String sql = "UPDATE categorias  " + "SET nome = ? WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setString(1, categoria.getNome());
			stmt.setInt(2, categoria.getId());

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao atualizar categoria: " + erro.getMessage());

			return false;
		}
	}

	public boolean excluir(int id) {

		if (!bd.connect()) {
			return false;
		}

		String sql = "DELETE FROM categorias WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();

			stmt.close();
			bd.close();

			return true;

		} catch (SQLException erro) {

			System.out.println("Erro ao excluir categoria: " + erro.getMessage());

			return false;
		}
	}

	public Categoria buscarPorId(int id) {
		if (!bd.connect()) {
			return null;
		}

		String sql = "SELECT * FROM categorias WHERE id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				Categoria categoria = new Categoria(
						rs.getInt("id"), 
						rs.getString("nome")
						);

				rs.close();
				stmt.close();
				bd.close();

				return categoria;
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {

			System.out.println("Erro ao buscar categoria: " + erro.getMessage());
		}

		return null;
	}

	public List<Categoria> listarPorUsuario(int idUsuario) {
		List<Categoria> categorias = new ArrayList<>();

		if (!bd.connect()) {
			return categorias;
		}

		String sql = "SELECT * FROM categorias WHERE usuario_id = ?";

		try {

			PreparedStatement stmt = bd.getConnection().prepareStatement(sql);

			stmt.setInt(1, idUsuario);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Categoria categoria = new Categoria(
						rs.getInt("id"), 
						rs.getString("nome")
						
						);

				categorias.add(categoria);
			}

			rs.close();
			stmt.close();
			bd.close();

		} catch (SQLException erro) {

			System.out.println("Erro ao listar as categorias: " + erro.getMessage());
		}

		return categorias;
	}

}