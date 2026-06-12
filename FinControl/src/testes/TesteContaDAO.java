package testes;

import dao.ContaDAO;
import enums.TipoConta;
import model.Conta;

public class TesteContaDAO {

    public static void main(String[] args) {

        ContaDAO dao = new ContaDAO();

        int idUsuario = 1;

        Conta conta = new Conta(
                0,
                "Conta Teste",
                1000.00,
                TipoConta.CONTA_BANCARIA
        );

        System.out.println("Cadastrar conta: " + dao.cadastrar(conta, idUsuario));

        for (Conta c : dao.listarPorUsuario(idUsuario)) {
            System.out.println(c.getId() + " - " + c.getNome() + " - " + c.getSaldo());
        }
    }
}