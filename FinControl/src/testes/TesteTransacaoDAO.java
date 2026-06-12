package testes;

import java.sql.Date;

import controller.TransacaoController;
import dao.CategoriaDAO;
import dao.ContaDAO;
import dao.UsuarioDAO;
import enums.StatusPagamento;
import model.Categoria;
import model.Conta;
import model.Despesa;
import model.Receita;
import model.Usuario;

public class TesteTransacaoDAO {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ContaDAO contaDAO = new ContaDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        TransacaoController controller = new TransacaoController();

        Usuario usuario = usuarioDAO.buscarPorId(3);
        Conta conta = contaDAO.buscarPorId(12);
        Categoria categoria = categoriaDAO.buscarPorId(18);

        if (usuario == null || conta == null || categoria == null) {
            System.out.println("Usuário, conta ou categoria não encontrados.");
            return;
        }

        Receita receita = new Receita(
                0,
                "Receita Teste",
                500.00,
                new Date(System.currentTimeMillis()),
                usuario,
                conta,
                categoria,
                "Teste"
        );

        boolean receitaOk = controller.cadastrarReceita(
                receita,
                usuario.getId(),
                conta.getId(),
                categoria.getId()
        );

        System.out.println("Cadastrar receita: " + receitaOk);

        Despesa despesa = new Despesa(
                0,
                "Despesa Teste",
                100.00,
                new Date(System.currentTimeMillis()),
                usuario,
                conta,
                categoria,
                StatusPagamento.PAGO
        );

        boolean despesaOk = controller.cadastrarDespesa(
                despesa,
                usuario.getId(),
                conta.getId(),
                categoria.getId()
        );

        System.out.println("Cadastrar despesa: " + despesaOk);
    }
}