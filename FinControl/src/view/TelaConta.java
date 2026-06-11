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
	private JButton btnExcluir;
	private JTable tabelaContas;

	public TelaConta(Usuario usuarioLogado) {

		this.usuarioLogado = usuarioLogado;
		this.contaController = new ContaController();

		setTitle("FinControl - Contas");
		setSize(650, 450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(30, 30, 80, 25);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(100, 30, 180, 25);
		getContentPane().add(txtNome);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(30, 70, 80, 25);
		getContentPane().add(lblSaldo);

		txtSaldo = new JTextField();
		txtSaldo.setBounds(100, 70, 180, 25);
		getContentPane().add(txtSaldo);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(30, 110, 80, 25);
		getContentPane().add(lblTipo);

		cbTipoConta = new JComboBox<>(TipoConta.values());
		cbTipoConta.setBounds(100, 110, 180, 25);
		getContentPane().add(cbTipoConta);

		btnSalvar = new JButton("Cadastrar");
		btnSalvar.setBounds(100, 150, 120, 30);
		getContentPane().add(btnSalvar);

		btnAtualizarLista = new JButton("Atualizar Lista");
		btnAtualizarLista.setBounds(230, 150, 140, 30);
		getContentPane().add(btnAtualizarLista);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(380, 150, 100, 30);
		getContentPane().add(btnExcluir);

		btnExcluir.addActionListener(e -> {
			int linha = tabelaContas.getSelectedRow();

			if (linha == -1) {
				JOptionPane.showMessageDialog(null, "Selecione uma conta na tabela.");
				return;
			}

			int idConta = Integer.parseInt(tabelaContas.getValueAt(linha, 0).toString());

			excluirConta(idConta);
		});

		tabelaContas = new JTable();

		JScrollPane scroll = new JScrollPane(tabelaContas);
		scroll.setBounds(30, 210, 570, 160);
		getContentPane().add(scroll);

		btnSalvar.addActionListener(e -> cadastrarConta());
		btnAtualizarLista.addActionListener(e -> carregarContas());

		carregarContas();
	}

	private void cadastrarConta() {

		try {

			String nome = txtNome.getText();
			double saldo = Double.parseDouble(txtSaldo.getText());
			TipoConta tipoConta = (TipoConta) cbTipoConta.getSelectedItem();

			boolean sucesso = contaController.cadastrar(nome, saldo, tipoConta, usuarioLogado.getId());

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

		List<Conta> contas = contaController.listarPorUsuario(usuarioLogado.getId());

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("Saldo");
		modelo.addColumn("Tipo");

		if (contas != null) {
			for (Conta conta : contas) {
				modelo.addRow(new Object[] { conta.getId(), conta.getNome(), conta.getSaldo(), conta.getTipoConta() });
			}
		}

		tabelaContas.setModel(modelo);
	}

	private void excluirConta(int idConta) {

		int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta conta?", "Confirmação",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (opcao == JOptionPane.YES_OPTION) {

			boolean sucesso = contaController.excluir(idConta);

			if (sucesso) {

				JOptionPane.showMessageDialog(this, "Conta excluída com sucesso!");

				carregarContas();

			} else {

				JOptionPane.showMessageDialog(this, "Erro ao excluir conta.");
			}
		}
	}
}