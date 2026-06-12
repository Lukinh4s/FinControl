package testes;

import dao.UsuarioDAO;
import model.Usuario;

public class TesteUsuarioDAO {

    public static void main(String[] args) {

        UsuarioDAO dao = new UsuarioDAO();

        Usuario usuario = new Usuario(
                "Usuário Teste",
                "teste@email.com",
                "123456"
        );

        System.out.println("Cadastrar usuário: " + dao.cadastrar(usuario));

        Usuario buscado = dao.buscarPorEmail("teste@email.com");

        if (buscado != null) {
            System.out.println("Usuário encontrado: " + buscado.getNome());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
}