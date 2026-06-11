package view;

import java.sql.Date;

import javax.swing.*;

import enums.StatusPagamento;
import model.Categoria;
import model.Conta;
import model.Despesa;
import model.Usuario;

public class TelaDespesa extends JFrame {

    private Usuario usuarioLogado;

    private JTextField txtDescricao;
    private JTextField txtValor;

    private JComboBox<StatusPagamento> cbStatus;

    public TelaDespesa(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;

        setTitle("Despesas");
        setSize(400,320);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(20,20,100,25);
        add(lblDescricao);

        txtDescricao = new JTextField();
        txtDescricao.setBounds(120,20,200,25);
        add(txtDescricao);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(20,60,100,25);
        add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(120,60,200,25);
        add(txtValor);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(20,100,100,25);
        add(lblStatus);

        cbStatus = new JComboBox<>(StatusPagamento.values());
        cbStatus.setBounds(120,100,200,25);
        add(cbStatus);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120,150,120,30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> salvar());
    }

    private void salvar() {

        Conta conta = null;
        Categoria categoria = null;

        Despesa despesa = new Despesa(
                0,
                txtDescricao.getText(),
                Double.parseDouble(txtValor.getText()),
                new Date(System.currentTimeMillis()),
                usuarioLogado,
                conta,
                categoria,
                (StatusPagamento) cbStatus.getSelectedItem()
        );

        JOptionPane.showMessageDialog(
                null,
                "Despesa criada!"
        );
    }
}