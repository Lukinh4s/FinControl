package testes;

import java.sql.Date;

import controller.TransacaoController;
import util.FormatadorMoeda;

public class TesteRelatorio {

    public static void main(String[] args) {

        TransacaoController controller = new TransacaoController();

        int idUsuario = 1;

        Date inicio = Date.valueOf("2026-01-01");
        Date fim = Date.valueOf("2026-12-31");

        double receitas = controller.calcularTotalReceitas(idUsuario, inicio, fim);
        double despesas = controller.calcularTotalDespesas(idUsuario, inicio, fim);
        double saldo = receitas - despesas;

        System.out.println("Receitas: " + FormatadorMoeda.formatar(receitas));
        System.out.println("Despesas: " + FormatadorMoeda.formatar(despesas));
        System.out.println("Saldo: " + FormatadorMoeda.formatar(saldo));
    }
}