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

        tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 80, 540, 240);
        add(scroll);

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

        for (Categoria categoria : categorias) {

            modelo.addRow(new Object[] {
                    categoria.getId(),
                    categoria.getNome()
            });
        }

        tabela.setModel(modelo);
    }
}