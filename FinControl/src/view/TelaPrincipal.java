package view;

import javax.swing.*;

import model.Usuario;

public class TelaPrincipal extends JFrame {

    private Usuario usuarioLogado;

    public TelaPrincipal(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;

        setTitle("FinControl - Tela Principal");
        setSize(550, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblBemVindo = new JLabel("Bem-vindo, " + usuarioLogado.getNome());
        lblBemVindo.setBounds(30, 20, 350, 25);
        add(lblBemVindo);

        JButton btnContas = new JButton("Contas");
        btnContas.setBounds(30, 70, 160, 35);
        add(btnContas);

        JButton btnCategorias = new JButton("Categorias");
        btnCategorias.setBounds(210, 70, 160, 35);
        add(btnCategorias);

        JButton btnReceitas = new JButton("Receitas");
        btnReceitas.setBounds(30, 120, 160, 35);
        add(btnReceitas);

        JButton btnDespesas = new JButton("Despesas");
        btnDespesas.setBounds(210, 120, 160, 35);
        add(btnDespesas);

        JButton btnRelatorios = new JButton("Relatórios");
        btnRelatorios.setBounds(30, 170, 160, 35);
        add(btnRelatorios);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(210, 170, 160, 35);
        add(btnSair);

        btnContas.addActionListener(e -> {
            new TelaConta(usuarioLogado).setVisible(true);
        });

        btnSair.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
    }
}