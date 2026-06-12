package testes;

import java.sql.Date;

import controller.CategoriaController;
import controller.ContaController;
import controller.TransacaoController;
import enums.StatusPagamento;
import enums.TipoConta;
import model.Categoria;
import model.Conta;
import model.Despesa;
import model.Receita;
import model.Usuario;
import dao.UsuarioDAO;
import util.FormatadorMoeda;

public class TesteSistemaCompleto {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ContaController contaController = new ContaController();
        CategoriaController categoriaController = new CategoriaController();
        TransacaoController transacaoController = new TransacaoController();

        Usuario usuario = usuarioDAO.buscarPorId(1);

        if (usuario == null) {
            System.out.println("Usuário id 1 não encontrado.");
            return;
        }

        System.out.println("Usuário: " + usuario.getNome());

        boolean contaOk = contaController.cadastrar(
                "Conta Sistema Teste",
                0,
                TipoConta.CONTA_BANCARIA,
                usuario.getId()
        );

        boolean categoriaOk = categoriaController.cadastrar(
                "Categoria Sistema Teste",
                usuario.getId()
        );

        System.out.println("Conta criada: " + contaOk);
        System.out.println("Categoria criada: " + categoriaOk);

        Conta conta = contaController.listarPorUsuario(usuario.getId()).get(0);
        Categoria categoria = categoriaController.listarPorUsuario(usuario.getId()).get(0);

        Receita receita = new Receita(
                0,
                "Salário Teste",
                3000,
                new Date(System.currentTimeMillis()),
                usuario,
                conta,
                categoria,
                "Empresa Teste"
        );

        transacaoController.cadastrarReceita(
                receita,
                usuario.getId(),
                conta.getId(),
                categoria.getId()
        );

        Despesa despesa = new Despesa(
                0,
                "Mercado Teste",
                250,
                new Date(System.currentTimeMillis()),
                usuario,
                conta,
                categoria,
                StatusPagamento.PAGO
        );

        transacaoController.cadastrarDespesa(
                despesa,
                usuario.getId(),
                conta.getId(),
                categoria.getId()
        );

        Date inicio = Date.valueOf("2026-01-01");
        Date fim = Date.valueOf("2026-12-31");

        double receitas = transacaoController.calcularTotalReceitas(usuario.getId(), inicio, fim);
        double despesas = transacaoController.calcularTotalDespesas(usuario.getId(), inicio, fim);

        System.out.println("Receitas: " + FormatadorMoeda.formatar(receitas));
        System.out.println("Despesas: " + FormatadorMoeda.formatar(despesas));
        System.out.println("Saldo: " + FormatadorMoeda.formatar(receitas - despesas));
    }
}