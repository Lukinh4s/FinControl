package view;

import javax.swing.*;

import controller.LoginController;
import model.Usuario;

public class TelaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnCadastrar;

    public TelaLogin() {

        setTitle("FinControl - Login");
        setSize(350, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 30, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 30, 190, 25);
        add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 70, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 70, 190, 25);
        add(txtSenha);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100, 115, 85, 30);
        add(btnEntrar);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(190, 115, 105, 30);
        add(btnCadastrar);

        btnEntrar.addActionListener(e -> fazerLogin());

        btnCadastrar.addActionListener(e -> {
            new TelaCadastroUsuario().setVisible(true);
            dispose();
        });
    }

    private void fazerLogin() {

        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        LoginController controller = new LoginController();

        Usuario usuarioLogado = controller.autenticar(email, senha);

        if (usuarioLogado != null) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

            new TelaPrincipal(usuarioLogado).setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha inválidos!");
        }
    }
}