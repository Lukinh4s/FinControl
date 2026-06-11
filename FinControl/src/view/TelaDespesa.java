package view;

import java.sql.Date;
import java.util.List;
import javax.swing.*;

import controller.CategoriaController;
import controller.ContaController;
import controller.TransacaoController;
import enums.StatusPagamento;
import model.Categoria;
import model.Conta;
import model.Despesa;
import model.Usuario;

public class TelaDespesa extends JFrame {

	private Usuario usuarioLogado;
	private JTextField txtDescricao, txtValor;
	private JComboBox<Conta> cbConta;
	private JComboBox<Categoria> cbCategoria;
	private JComboBox<StatusPagamento> cbStatus;

	private ContaController contaController = new ContaController();
	private CategoriaController categoriaController = new CategoriaController();
	private TransacaoController transacaoController = new TransacaoController();

	public TelaDespesa(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;

		setTitle("FinControl - Despesa");
		setSize(450, 350);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(30, 30, 100, 25);
		add(lblDescricao);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(140, 30, 220, 25);
		add(txtDescricao);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(30, 70, 100, 25);
		add(lblValor);

		txtValor = new JTextField();
		txtValor.setBounds(140, 70, 220, 25);
		add(txtValor);

		JLabel lblConta = new JLabel("Conta:");
		lblConta.setBounds(30, 110, 100, 25);
		add(lblConta);

		cbConta = new JComboBox<>();
		cbConta.setBounds(140, 110, 220, 25);
		add(cbConta);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(30, 150, 100, 25);
		add(lblCategoria);

		cbCategoria = new JComboBox<>();
		cbCategoria.setBounds(140, 150, 220, 25);
		add(cbCategoria);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(30, 190, 100, 25);
		add(lblStatus);

		cbStatus = new JComboBox<>(StatusPagamento.values());
		cbStatus.setBounds(140, 190, 220, 25);
		add(cbStatus);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(140, 240, 100, 30);
		add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(260, 240, 100, 30);
		add(btnLimpar);

		btnLimpar.addActionListener(e -> limparCampos());

		btnSalvar.addActionListener(e -> salvarDespesa());

		carregarCombos();
	}

	private void carregarCombos() {
		List<Conta> contas = contaController.listarPorUsuario(usuarioLogado.getId());
		List<Categoria> categorias = categoriaController.listarPorUsuario(usuarioLogado.getId());

		for (Conta conta : contas) {
			cbConta.addItem(conta);
		}

		for (Categoria categoria : categorias) {
			cbCategoria.addItem(categoria);
		}
	}

	private void salvarDespesa() {
		try {
			Conta conta = (Conta) cbConta.getSelectedItem();
			Categoria categoria = (Categoria) cbCategoria.getSelectedItem();

			if (conta == null || categoria == null) {
				JOptionPane.showMessageDialog(null, "Cadastre uma conta e uma categoria antes.");
				return;
			}

			Despesa despesa = new Despesa(0, txtDescricao.getText(), Double.parseDouble(txtValor.getText()),
					new Date(System.currentTimeMillis()), usuarioLogado, conta, categoria,
					(StatusPagamento) cbStatus.getSelectedItem());

			boolean sucesso = transacaoController.cadastrarDespesa(despesa, usuarioLogado.getId(), conta.getId(),
					categoria.getId());

			if (sucesso) {
				JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso!");
				txtDescricao.setText("");
				txtValor.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar despesa. Verifique o saldo da conta.");
			}

		} catch (NumberFormatException erro) {
			JOptionPane.showMessageDialog(null, "Digite um valor válido.");
		}
	}

	private void limparCampos() {

		txtDescricao.setText("");
		txtValor.setText("");

		if (cbConta.getItemCount() > 0) {
			cbConta.setSelectedIndex(0);
		}

		if (cbCategoria.getItemCount() > 0) {
			cbCategoria.setSelectedIndex(0);
		}

		cbStatus.setSelectedIndex(0);
	}
}