package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.*;

import controller.TransacaoController;
import model.Usuario;
import util.FormatadorMoeda;

public class TelaPrincipal extends JFrame {

    private Usuario usuarioLogado;

    private JLabel lblSaldoTotal;
    private JLabel lblReceitasMes;
    private JLabel lblDespesasMes;

    public TelaPrincipal(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;

        setTitle("FinControl - Tela Principal");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblBemVindo = new JLabel("Bem-vindo, " + usuarioLogado.getNome());
        lblBemVindo.setBounds(30, 20, 350, 25);
        add(lblBemVindo);

        lblSaldoTotal = new JLabel("Saldo total: R$ 0,00");
        lblSaldoTotal.setBounds(30, 50, 300, 25);
        add(lblSaldoTotal);

        lblReceitasMes = new JLabel("Receitas do mês: R$ 0,00");
        lblReceitasMes.setBounds(30, 80, 300, 25);
        add(lblReceitasMes);

        lblDespesasMes = new JLabel("Despesas do mês: R$ 0,00");
        lblDespesasMes.setBounds(30, 110, 300, 25);
        add(lblDespesasMes);

        JButton btnAtualizar = new JButton("Atualizar Saldo");
        btnAtualizar.setBounds(380, 50, 150, 30);
        add(btnAtualizar);

        JButton btnContas = new JButton("Contas");
        btnContas.setBounds(30, 160, 160, 35);
        add(btnContas);

        JButton btnCategorias = new JButton("Categorias");
        btnCategorias.setBounds(210, 160, 160, 35);
        add(btnCategorias);

        JButton btnReceitas = new JButton("Receitas");
        btnReceitas.setBounds(30, 210, 160, 35);
        add(btnReceitas);

        JButton btnDespesas = new JButton("Despesas");
        btnDespesas.setBounds(210, 210, 160, 35);
        add(btnDespesas);

        JButton btnRelatorios = new JButton("Relatórios");
        btnRelatorios.setBounds(30, 260, 160, 35);
        add(btnRelatorios);

        JButton btnHistorico = new JButton("Histórico");
        btnHistorico.setBounds(210, 260, 160, 35);
        add(btnHistorico);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(30, 310, 160, 35);
        add(btnSair);

        btnAtualizar.addActionListener(e -> atualizarDashboard());

        btnContas.addActionListener(e -> abrirTelaComAtualizacao(new TelaConta(usuarioLogado)));
        btnCategorias.addActionListener(e -> abrirTelaComAtualizacao(new TelaCategoria(usuarioLogado)));
        btnReceitas.addActionListener(e -> abrirTelaComAtualizacao(new TelaReceita(usuarioLogado)));
        btnDespesas.addActionListener(e -> abrirTelaComAtualizacao(new TelaDespesa(usuarioLogado)));
        btnRelatorios.addActionListener(e -> abrirTelaComAtualizacao(new TelaRelatorio(usuarioLogado)));
        btnHistorico.addActionListener(e -> abrirTelaComAtualizacao(new TelaHistoricoTransacoes(usuarioLogado)));

        btnSair.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        atualizarDashboard();
    }

    private void abrirTelaComAtualizacao(JFrame tela) {

        tela.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                atualizarDashboard();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                atualizarDashboard();
            }
        });

        tela.setVisible(true);
    }

    private void atualizarDashboard() {

        TransacaoController controller = new TransacaoController();

        double saldoTotal = controller.calcularSaldoTotalUsuario(
                usuarioLogado.getId()
        );

        LocalDate hoje = LocalDate.now();
        LocalDate primeiroDia = hoje.withDayOfMonth(1);
        LocalDate ultimoDia = hoje.withDayOfMonth(
                hoje.lengthOfMonth()
        );

        Date inicioMes = Date.valueOf(primeiroDia);
        Date fimMes = Date.valueOf(ultimoDia);

        double receitasMes = controller.calcularTotalReceitas(
                usuarioLogado.getId(),
                inicioMes,
                fimMes
        );

        double despesasMes = controller.calcularTotalDespesas(
                usuarioLogado.getId(),
                inicioMes,
                fimMes
        );

        lblSaldoTotal.setText(
                "Saldo total: " +
                FormatadorMoeda.formatar(saldoTotal)
        );

        lblReceitasMes.setText(
                "Receitas do mês: " +
                FormatadorMoeda.formatar(receitasMes)
        );

        lblDespesasMes.setText(
                "Despesas do mês: " +
                FormatadorMoeda.formatar(despesasMes)
        );
    }
}