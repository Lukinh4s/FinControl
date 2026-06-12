package testes;

import dao.CategoriaDAO;
import model.Categoria;

public class TesteCategoriaDAO {

    public static void main(String[] args) {

        CategoriaDAO dao = new CategoriaDAO();

        int idUsuario = 1;

        Categoria categoria = new Categoria(
                0,
                "Categoria Teste"
        );

        System.out.println("Cadastrar categoria: " + dao.cadastrar(categoria, idUsuario));

        for (Categoria c : dao.listarPorUsuario(idUsuario)) {
            System.out.println(c.getId() + " - " + c.getNome());
        }
    }
}