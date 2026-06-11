package view;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.CategoriaController;
import model.Categoria;
import model.Usuario;

public class TelaCategoria extends JFrame {

    private Usuario usuarioLogado;
    private CategoriaController controller;

    private JTextField txtNome;
    private JTable tabela;

    public TelaCategoria(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;
        this.controller = new CategoriaController();

        setTitle("Categorias");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 50, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(80, 20, 180, 25);
        add(txtNome);

        JButton btnSalvar = new JButton("Cadastrar");
        btnSalvar.setBounds(280, 20, 120, 25);
        add(btnSalvar);
        
        JButton btnExcluir = new JButton("Exluir");
        btnExcluir.setBounds(420, 20, 120, 25);
        add(btnExcluir);

        tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 80, 540, 240);
        add(scroll);
        
        btnExcluir.addActionListener(e -> {
			int linha = tabela.getSelectedRow();

			if (linha == -1) {
				JOptionPane.showMessageDialog(null, "Selecione uma categoria na tabela.");
				return;
			}

			int idCategoria = Integer.parseInt(tabela.getValueAt(linha, 0).toString());

			excluirCategoria(idCategoria);
		});

        btnSalvar.addActionListener(e -> cadastrar());

        carregarCategorias();
    }

    private void cadastrar() {

        boolean sucesso = controller.cadastrar(
                txtNome.getText(),
                usuarioLogado.getId()
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Categoria cadastrada!");
            txtNome.setText("");
            carregarCategorias();
        }
    }

    private void carregarCategorias() {

        List<Categoria> categorias =
                controller.listarPorUsuario(
                        usuarioLogado.getId()
                );

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");

        if (categorias != null) {

            for (Categoria categoria : categorias) {

                modelo.addRow(new Object[] {
                        categoria.getId(),
                        categoria.getNome()
                });
            }
        }

        tabela.setModel(modelo);
    }
    
    private void excluirCategoria(int idCategoria) {

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir esta categoria?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcao == JOptionPane.YES_OPTION) {

            boolean sucesso =
                    controller.excluir(idCategoria);

            if (sucesso) {

                JOptionPane.showMessageDialog(
                        this,
                        "Categoria excluída com sucesso!"
                );

                carregarCategorias();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao excluir categoria."
                );
            }
        }
    }
}