package view;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.TransacaoController;
import model.Transacao;
import model.Usuario;
import util.FormatadorMoeda;

public class TelaHistoricoTransacoes extends JFrame {

    private Usuario usuarioLogado;
    private JTable tabela;
    private TransacaoController controller;

    public TelaHistoricoTransacoes(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        this.controller = new TransacaoController();

        setTitle("Histórico de Transações");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        tabela = new JTable();

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 20, 690, 320);
        add(scroll);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(20, 360, 120, 30);
        add(btnAtualizar);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBounds(160, 360, 120, 30);
        add(btnFechar);

        btnAtualizar.addActionListener(e -> carregarTabela());
        btnFechar.addActionListener(e -> dispose());

        carregarTabela();
    }

    private void carregarTabela() {

        List<Transacao> transacoes = controller.listarPorUsuario(usuarioLogado.getId());

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Data");
        modelo.addColumn("Descrição");
        modelo.addColumn("Tipo");
        modelo.addColumn("Conta");
        modelo.addColumn("Categoria");
        modelo.addColumn("Valor");

        if (transacoes != null) {
            for (Transacao t : transacoes) {
                modelo.addRow(new Object[] {
                        t.getId(),
                        t.getData(),
                        t.getDescricao(),
                        t.getTipoTransacao(),
                        t.getConta().getNome(),
                        t.getCategoria().getNome(),
                        FormatadorMoeda.formatar(t.calcularValor())
                });
            }
        }

        tabela.setModel(modelo);
    }
}