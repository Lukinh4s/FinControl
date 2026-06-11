package view;

import javax.swing.*;

import controller.UsuarioController;

public class TelaCadastroUsuario extends JFrame {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnSalvar;
    private JButton btnVoltar;

    public TelaCadastroUsuario() {

        setTitle("FinControl - Cadastro de Usuário");
        setSize(400, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 30, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(110, 30, 220, 25);
        add(txtNome);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 70, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(110, 70, 220, 25);
        add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 110, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(110, 110, 220, 25);
        add(txtSenha);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(110, 160, 100, 30);
        add(btnSalvar);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(220, 160, 100, 30);
        add(btnVoltar);

        btnSalvar.addActionListener(e -> cadastrarUsuario());

        btnVoltar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
    }

    private void cadastrarUsuario() {

        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        UsuarioController controller = new UsuarioController();

        boolean sucesso = controller.cadastrar(nome, email, senha);

        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

            new TelaLogin().setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário!");
        }
    }
}