package controller;

import java.util.List;

import dao.CategoriaDAO;
import model.Categoria;

public class CategoriaController {

	private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        this.categoriaDAO = new CategoriaDAO();
    }
    
    public boolean cadastrar(String nome, int idUsuario) {

		if (nome == null || nome.isEmpty()) {
			return false;
		}
		
		if (idUsuario <= 0) {
			return false;
		}

		Categoria categoria = new Categoria(0, nome );

		return categoriaDAO.cadastrar(categoria, idUsuario);

	}
    
    public boolean atualizar(Categoria categoria) {

		if (categoria == null) {
			return false;
		}

		if (categoria.getId() <= 0) {
			return false;
		}

		if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
			return false;
		}

		return categoriaDAO.atualizar(categoria);
	}

	public boolean excluir(int idCategoria) {

		if (idCategoria <= 0) {
			return false;
		}

		return categoriaDAO.excluir(idCategoria);
	}

	public Categoria buscarPorId(int idCategoria) {

		if (idCategoria <= 0) {
			return null;
		}

		return categoriaDAO.buscarPorId(idCategoria);
	}
	
	public List<Categoria> listarPorUsuario(int idUsuario) {

		if (idUsuario <= 0) {
			return null;
		}

		return categoriaDAO.listarPorUsuario(idUsuario);
	}

	/*public List<Categoria> listarPorNome(int idUsuario, String nome) {

		if (idUsuario <= 0) {
			return null;
		}

		if (nome == null || nome.isEmpty()) {
			return listarPorUsuario(idUsuario);
		}

		return categoriaDAO.listarPorNome(idUsuario, nome);
	}*/ //Seria interessante implementar para caso fosse pesquisar por nome?
}
    

