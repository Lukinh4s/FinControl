package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class LoginController {

	private UsuarioDAO usuarioDAO;

	public LoginController() {
		this.usuarioDAO = new UsuarioDAO();
	}

	public Usuario autenticar(String email, String senha) {

		if (email == null || email.isEmpty()) {
			return null;
		}

		if (senha == null || senha.isEmpty()) {
			return null;
		}

		Usuario usuario = usuarioDAO.buscarPorEmail(email);

		if (usuario == null) {
			return null;
		}

		if (usuario.login(email, senha)) {
			return usuario;
		}

		return null;
	}

}
