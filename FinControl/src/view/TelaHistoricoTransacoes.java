package view;

import java.sql.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.CategoriaController;
import controller.TransacaoController;
import model.Categoria;
import model.Transacao;
import model.Usuario;
import util.FormatadorMoeda;

public class TelaHistoricoTransacoes extends JFrame {

    private Usuario usuarioLogado;
    private TransacaoController controller;
    private CategoriaController categoriaController;

    private JTextField txtDataInicio;
    private JTextField txtDataFim;

    private JComboBox<String> cbTipo;
    private JComboBox<Categoria> cbCategoria;

    private JTable tabela;

    private JLabel lblReceitas;
    private JLabel lblDespesas;
    private JLabel lblSaldo;

    public TelaHistoricoTransacoes(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        this.controller = new TransacaoController();
        this.categoriaController = new CategoriaController();

        setTitle("FinControl - Histórico de Transações");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblInicio = new JLabel("Data início:");
        lblInicio.setBounds(20, 20, 80, 25);
        add(lblInicio);

        txtDataInicio = new JTextField("2026-01-01");
        txtDataInicio.setBounds(100, 20, 100, 25);
        add(txtDataInicio);

        JLabel lblFim = new JLabel("Data fim:");
        lblFim.setBounds(220, 20, 70, 25);
        add(lblFim);

        txtDataFim = new JTextField("2026-12-31");
        txtDataFim.setBounds(280, 20, 100, 25);
        add(txtDataFim);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(400, 20, 50, 25);
        add(lblTipo);

        cbTipo = new JComboBox<>();
        cbTipo.addItem("TODAS");
        cbTipo.addItem("RECEITA");
        cbTipo.addItem("DESPESA");
        cbTipo.setBounds(440, 20, 110, 25);
        add(cbTipo);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(570, 20, 80, 25);
        add(lblCategoria);

        cbCategoria = new JComboBox<>();
        cbCategoria.setBounds(645, 20, 160, 25);
        add(cbCategoria);

        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setBounds(20, 60, 120, 30);
        add(btnPesquisar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(150, 60, 100, 30);
        add(btnLimpar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(260, 60, 100, 30);
        add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(370, 60, 100, 30);
        add(btnExcluir);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBounds(480, 60, 100, 30);
        add(btnFechar);

        tabela = new JTable();

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 110, 790, 300);
        add(scroll);

        lblReceitas = new JLabel("Receitas: R$ 0,00");
        lblReceitas.setBounds(20, 430, 250, 25);
        add(lblReceitas);

        lblDespesas = new JLabel("Despesas: R$ 0,00");
        lblDespesas.setBounds(280, 430, 250, 25);
        add(lblDespesas);

        lblSaldo = new JLabel("Saldo: R$ 0,00");
        lblSaldo.setBounds(540, 430, 250, 25);
        add(lblSaldo);

        carregarCategorias();

        btnPesquisar.addActionListener(e -> pesquisar());
        btnLimpar.addActionListener(e -> limparFiltros());
        btnEditar.addActionListener(e -> editarTransacao());
        btnExcluir.addActionListener(e -> excluirTransacao());
        btnFechar.addActionListener(e -> dispose());

        pesquisar();
    }

    private void carregarCategorias() {
        cbCategoria.removeAllItems();
        cbCategoria.addItem(null);

        List<Categoria> categorias =
                categoriaController.listarPorUsuario(usuarioLogado.getId());

        if (categorias != null) {
            for (Categoria categoria : categorias) {
                cbCategoria.addItem(categoria);
            }
        }
    }

    private void pesquisar() {
        try {
            Date inicio = Date.valueOf(txtDataInicio.getText());
            Date fim = Date.valueOf(txtDataFim.getText());

            if (fim.before(inicio)) {
                JOptionPane.showMessageDialog(
                        this,
                        "A data final não pode ser menor que a data inicial."
                );
                return;
            }

            List<Transacao> transacoes =
                    controller.listarPorPeriodo(
                            usuarioLogado.getId(),
                            inicio,
                            fim
                    );

            String tipoSelecionado =
                    cbTipo.getSelectedItem().toString();

            Categoria categoriaSelecionada =
                    (Categoria) cbCategoria.getSelectedItem();

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("ID");
            modelo.addColumn("Data");
            modelo.addColumn("Descrição");
            modelo.addColumn("Tipo");
            modelo.addColumn("Conta");
            modelo.addColumn("Categoria");
            modelo.addColumn("Valor");

            double totalReceitas = 0;
            double totalDespesas = 0;

            if (transacoes != null) {
                for (Transacao t : transacoes) {

                    if (!tipoSelecionado.equals("TODAS")
                            && !t.getTipoTransacao().name().equals(tipoSelecionado)) {
                        continue;
                    }

                    if (categoriaSelecionada != null
                            && t.getCategoria().getId() != categoriaSelecionada.getId()) {
                        continue;
                    }

                    double valorCalculado = t.calcularValor();

                    if (valorCalculado >= 0) {
                        totalReceitas += valorCalculado;
                    } else {
                        totalDespesas += Math.abs(valorCalculado);
                    }

                    modelo.addRow(new Object[] {
                            t.getId(),
                            t.getData(),
                            t.getDescricao(),
                            t.getTipoTransacao(),
                            t.getConta().getNome(),
                            t.getCategoria().getNome(),
                            FormatadorMoeda.formatar(valorCalculado)
                    });
                }
            }

            tabela.setModel(modelo);

            lblReceitas.setText("Receitas: " + FormatadorMoeda.formatar(totalReceitas));
            lblDespesas.setText("Despesas: " + FormatadorMoeda.formatar(totalDespesas));
            lblSaldo.setText("Saldo: " + FormatadorMoeda.formatar(totalReceitas - totalDespesas));

        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite as datas no formato AAAA-MM-DD."
            );
        }
    }

    private void limparFiltros() {
        txtDataInicio.setText("2026-01-01");
        txtDataFim.setText("2026-12-31");
        cbTipo.setSelectedIndex(0);

        if (cbCategoria.getItemCount() > 0) {
            cbCategoria.setSelectedIndex(0);
        }

        pesquisar();
    }

    private void excluirTransacao() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma transação.");
            return;
        }

        int idTransacao =
                Integer.parseInt(
                        tabela.getValueAt(linha, 0).toString()
                );

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir esta transação?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcao == JOptionPane.YES_OPTION) {
            boolean sucesso = controller.excluir(idTransacao);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Transação excluída com sucesso!");
                pesquisar();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir transação.");
            }
        }
    }

    private void editarTransacao() {
        JOptionPane.showMessageDialog(
                this,
                "A edição detalhada de transações pode ser implementada depois.\n" +
                "Por enquanto, use excluir e cadastrar novamente."
        );
    }
}