package view;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ContaController;
import enums.TipoConta;
import model.Conta;
import model.Usuario;

public class TelaConta extends JFrame {

    private Usuario usuarioLogado;
    private ContaController contaController;

    private JTextField txtNome;
    private JTextField txtSaldo;
    private JComboBox<TipoConta> cbTipoConta;
    private JButton btnSalvar;
    private JButton btnAtualizarLista;
    private JTable tabelaContas;

    public TelaConta(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;
        this.contaController = new ContaController();

        setTitle("FinControl - Contas");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 30, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 30, 180, 25);
        add(txtNome);

        JLabel lblSaldo = new JLabel("Saldo:");
        lblSaldo.setBounds(30, 70, 80, 25);
        add(lblSaldo);

        txtSaldo = new JTextField();
        txtSaldo.setBounds(100, 70, 180, 25);
        add(txtSaldo);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(30, 110, 80, 25);
        add(lblTipo);

        cbTipoConta = new JComboBox<>(TipoConta.values());
        cbTipoConta.setBounds(100, 110, 180, 25);
        add(cbTipoConta);

        btnSalvar = new JButton("Cadastrar");
        btnSalvar.setBounds(100, 150, 120, 30);
        add(btnSalvar);

        btnAtualizarLista = new JButton("Atualizar Lista");
        btnAtualizarLista.setBounds(230, 150, 140, 30);
        add(btnAtualizarLista);

        tabelaContas = new JTable();

        JScrollPane scroll = new JScrollPane(tabelaContas);
        scroll.setBounds(30, 210, 570, 160);
        add(scroll);

        btnSalvar.addActionListener(e -> cadastrarConta());
        btnAtualizarLista.addActionListener(e -> carregarContas());

        carregarContas();
    }

    private void cadastrarConta() {

        try {

            String nome = txtNome.getText();
            double saldo = Double.parseDouble(txtSaldo.getText());
            TipoConta tipoConta = (TipoConta) cbTipoConta.getSelectedItem();

            boolean sucesso = contaController.cadastrar(
                    nome,
                    saldo,
                    tipoConta,
                    usuarioLogado.getId()
            );

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");

                txtNome.setText("");
                txtSaldo.setText("");

                carregarContas();

            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar conta!");
            }

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(null, "Digite um saldo válido!");
        }
    }

    private void carregarContas() {

        List<Conta> contas = contaController.listarPorUsuario(
                usuarioLogado.getId()
        );

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Saldo");
        modelo.addColumn("Tipo");

        if (contas != null) {
            for (Conta conta : contas) {
                modelo.addRow(new Object[] {
                        conta.getId(),
                        conta.getNome(),
                        conta.getSaldo(),
                        conta.getTipoConta()
                });
            }
        }

        tabelaContas.setModel(modelo);
    }
}