package view;

import java.sql.Date;

import javax.swing.*;

import model.Conta;
import model.Categoria;
import model.Receita;
import model.Usuario;

public class TelaReceita extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado;

    private JTextField txtDescricao;
    private JTextField txtValor;
    private JTextField txtFonte;

    public TelaReceita(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;

        setTitle("Receitas");
        setSize(400,300);
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

        JLabel lblFonte = new JLabel("Fonte:");
        lblFonte.setBounds(20,100,100,25);
        add(lblFonte);

        txtFonte = new JTextField();
        txtFonte.setBounds(120,100,200,25);
        add(txtFonte);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120,150,120,30);
        add(btnSalvar);

        btnSalvar.addActionListener(e -> salvar());
    }

    private void salvar() {

        // Exemplo simples
        Conta conta = null;
        Categoria categoria = null;

        Receita receita = new Receita(
                0,
                txtDescricao.getText(),
                Double.parseDouble(txtValor.getText()),
                new Date(System.currentTimeMillis()),
                usuarioLogado,
                conta,
                categoria,
                txtFonte.getText()
        );

        JOptionPane.showMessageDialog(
                null,
                "Receita criada!"
        );
    }
}