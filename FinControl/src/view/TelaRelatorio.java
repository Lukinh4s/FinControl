package view;

import java.sql.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.CategoriaController;
import controller.TransacaoController;
import model.Categoria;
import model.Usuario;
import util.FormatadorMoeda;

public class TelaRelatorio extends JFrame {

	private Usuario usuarioLogado;
	private TransacaoController controller;

	private JTextField txtDataInicio;
	private JTextField txtDataFim;

	private JLabel lblReceitas;
	private JLabel lblDespesas;
	private JLabel lblSaldo;

	private JTable tabelaCategorias;

	private PainelGrafico painelGrafico;

	public TelaRelatorio(Usuario usuarioLogado) {

		this.usuarioLogado = usuarioLogado;
		this.controller = new TransacaoController();

		setTitle("FinControl - Relatórios");
		setSize(650, 680);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblInicio = new JLabel("Data Início:");
		lblInicio.setBounds(30, 30, 100, 25);
		getContentPane().add(lblInicio);

		txtDataInicio = new JTextField("2026-01-01");
		txtDataInicio.setBounds(110, 30, 110, 25);
		getContentPane().add(txtDataInicio);

		JLabel lblFim = new JLabel("Data Fim:");
		lblFim.setBounds(240, 30, 100, 25);
		getContentPane().add(lblFim);

		txtDataFim = new JTextField("2026-12-31");
		txtDataFim.setBounds(310, 30, 110, 25);
		getContentPane().add(txtDataFim);

		JButton btnGerar = new JButton("Período");
		btnGerar.setBounds(440, 30, 90, 25);
		getContentPane().add(btnGerar);

		JButton btnRelatorioCategorias = new JButton("Categorias");
		btnRelatorioCategorias.setBounds(440, 65, 120, 25);
		getContentPane().add(btnRelatorioCategorias);

		lblReceitas = new JLabel("Receitas: R$ 0,00");
		lblReceitas.setBounds(30, 75, 250, 25);
		getContentPane().add(lblReceitas);

		lblDespesas = new JLabel("Despesas: R$ 0,00");
		lblDespesas.setBounds(30, 105, 250, 25);
		getContentPane().add(lblDespesas);

		lblSaldo = new JLabel("Saldo Final: R$ 0,00");
		lblSaldo.setBounds(30, 135, 250, 25);
		getContentPane().add(lblSaldo);

		painelGrafico = new PainelGrafico();
		painelGrafico.setBounds(30, 170, 560, 180);
		getContentPane().add(painelGrafico);

		tabelaCategorias = new JTable();

		JScrollPane scrollCategorias = new JScrollPane(tabelaCategorias);
		scrollCategorias.setBounds(30, 370, 560, 250);
		getContentPane().add(scrollCategorias);

		btnGerar.addActionListener(e -> gerarRelatorio());
		btnRelatorioCategorias.addActionListener(e -> gerarRelatorioCategorias());
	}

	private void gerarRelatorio() {

		try {

			Date inicio = Date.valueOf(txtDataInicio.getText());
			Date fim = Date.valueOf(txtDataFim.getText());

			if (fim.before(inicio)) {
				JOptionPane.showMessageDialog(null, "A data final não pode ser menor que a data inicial.");
				return;
			}

			double receitas = controller.calcularTotalReceitas(usuarioLogado.getId(), inicio, fim);

			double despesas = controller.calcularTotalDespesas(usuarioLogado.getId(), inicio, fim);

			double saldo = receitas - despesas;

			lblReceitas.setText("Receitas: " + FormatadorMoeda.formatar(receitas));
			lblDespesas.setText("Despesas: " + FormatadorMoeda.formatar(despesas));
			lblSaldo.setText("Saldo Final: " + FormatadorMoeda.formatar(saldo));

			painelGrafico.atualizarValores(receitas, despesas);

		} catch (IllegalArgumentException erro) {

			JOptionPane.showMessageDialog(null, "Digite as datas no formato: AAAA-MM-DD");
		}
	}

	private void gerarRelatorioCategorias() {

		CategoriaController categoriaController = new CategoriaController();

		List<Categoria> categorias = categoriaController.listarPorUsuario(usuarioLogado.getId());

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Categoria");
		modelo.addColumn("Total");

		if (categorias != null) {

			for (Categoria categoria : categorias) {

				double total = controller.calcularTotalPorCategoria(usuarioLogado.getId(), categoria.getId());

				if (total != 0) {

					modelo.addRow(new Object[] { categoria.getNome(), FormatadorMoeda.formatar(total) });

				}
			}
		}

		tabelaCategorias.setModel(modelo);
	}
}