package view;

import java.sql.Date;

import javax.swing.*;

import controller.TransacaoController;
import model.Usuario;

public class TelaRelatorio extends JFrame {

    private Usuario usuarioLogado;

    private JTextArea areaRelatorio;

    public TelaRelatorio(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;

        setTitle("Relatórios");
        setSize(600,400);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnGerar = new JButton("Gerar Relatório");
        btnGerar.setBounds(20,20,180,30);
        add(btnGerar);

        areaRelatorio = new JTextArea();

        JScrollPane scroll =
                new JScrollPane(areaRelatorio);

        scroll.setBounds(20,70,540,260);
        add(scroll);

        btnGerar.addActionListener(
                e -> gerarRelatorio()
        );
    }

    private void gerarRelatorio() {

        TransacaoController controller =
                new TransacaoController();

        double saldo =
                controller.calcularSaldoPeriodo(
                        usuarioLogado.getId(),
                        Date.valueOf("2025-01-01"),
                        Date.valueOf("2030-12-31")
                );

        areaRelatorio.setText(
                "Saldo do período: R$ "
                        + saldo
        );
    }
}