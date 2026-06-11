package view;

import javax.swing.*;

import model.Usuario;
import controller.TransacaoController;
import util.FormatadorMoeda;

public class TelaPrincipal extends JFrame {

    private Usuario usuarioLogado;
    private JLabel lblSaldoTotal;
    
    

    public TelaPrincipal(Usuario usuarioLogado) {
    	
    	TransacaoController transacaoController = new TransacaoController();

    	double saldoTotal = transacaoController.calcularSaldoTotalUsuario(usuarioLogado.getId());

    	lblSaldoTotal = new JLabel("Saldo total: " + FormatadorMoeda.formatar(saldoTotal));
    	lblSaldoTotal.setBounds(30, 45, 300, 25);
    	getContentPane().add(lblSaldoTotal);

        this.usuarioLogado = usuarioLogado;

        setTitle("FinControl - Tela Principal");
        setSize(550, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
       

        JLabel lblBemVindo = new JLabel("Bem-vindo, " + usuarioLogado.getNome());
        lblBemVindo.setBounds(30, 20, 350, 25);
        getContentPane().add(lblBemVindo);
        
        JButton btnHistorico = new JButton("Histórico");
        btnHistorico.setBounds(210, 170, 160, 35);
        getContentPane().add(btnHistorico);

        btnHistorico.addActionListener(e -> {
            new TelaHistoricoTransacoes(usuarioLogado).setVisible(true);
        });

        JButton btnContas = new JButton("Contas");
        btnContas.setBounds(30, 70, 160, 35);
        getContentPane().add(btnContas);

        JButton btnCategorias = new JButton("Categorias");
        btnCategorias.setBounds(210, 70, 160, 35);
        getContentPane().add(btnCategorias);

        JButton btnReceitas = new JButton("Receitas");
        btnReceitas.setBounds(30, 120, 160, 35);
        getContentPane().add(btnReceitas);

        JButton btnDespesas = new JButton("Despesas");
        btnDespesas.setBounds(210, 120, 160, 35);
        getContentPane().add(btnDespesas);

        JButton btnRelatorios = new JButton("Relatórios");
        btnRelatorios.setBounds(30, 170, 160, 35);
        getContentPane().add(btnRelatorios);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(210, 216, 160, 35);
        getContentPane().add(btnSair);
        
        btnCategorias.addActionListener(e -> {
            new TelaCategoria(usuarioLogado).setVisible(true);
        });

        btnReceitas.addActionListener(e -> {
            new TelaReceita(usuarioLogado).setVisible(true);
        });

        btnDespesas.addActionListener(e -> {
            new TelaDespesa(usuarioLogado).setVisible(true);
        });

        btnRelatorios.addActionListener(e -> {
            new TelaRelatorio(usuarioLogado).setVisible(true);
        });

        btnContas.addActionListener(e -> {
            new TelaConta(usuarioLogado).setVisible(true);
        });

        btnSair.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
    }
}