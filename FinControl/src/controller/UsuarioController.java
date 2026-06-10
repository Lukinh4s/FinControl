package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean cadastrar(String nome, String email, String senha) {

        if (nome == null || nome.isEmpty()) {
            return false;
        }

        if (email == null || email.isEmpty()) {
            return false;
        }

        if (senha == null || senha.isEmpty()) {
            return false;
        }

        Usuario usuarioExistente = usuarioDAO.buscarPorEmail(email);

        if (usuarioExistente != null) {
            return false;
        }

        Usuario usuario = new Usuario(nome, email, senha);

        return usuarioDAO.cadastrar(usuario);
    }

    public boolean atualizar(Usuario usuario) {

        if (usuario == null) {
            return false;
        }

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            return false;
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            return false;
        }

        return usuarioDAO.atualizar(usuario);
    }

    public boolean excluir(int id) {

        if (id <= 0) {
            return false;
        }

        return usuarioDAO.excluir(id);
    }

    public Usuario buscarPorId(int id) {

        if (id <= 0) {
            return null;
        }

        return usuarioDAO.buscarPorId(id);
    }

    public Usuario buscarPorEmail(String email) {

        if (email == null || email.isEmpty()) {
            return null;
        }

        return usuarioDAO.buscarPorEmail(email);
    }
}