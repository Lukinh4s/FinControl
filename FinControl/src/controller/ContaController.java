package controller;

import java.util.List;

import dao.ContaDAO;
import enums.TipoConta;
import model.Conta;

public class ContaController {

	private ContaDAO contaDAO;

	public ContaController() {
		this.contaDAO = new ContaDAO();
	}

	public boolean cadastrar(String nome, double saldo, TipoConta tipoConta, int idUsuario) {

		if (nome == null || nome.isEmpty()) {
			return false;
		}
		if (saldo < 0) {
			return false;
		}
		if (tipoConta == null) {
			return false;
		}
		if (idUsuario <= 0) {
			return false;
		}

		Conta conta = new Conta(0, nome, saldo, tipoConta);

		return contaDAO.cadastrar(conta, idUsuario);

	}

	public boolean atualizar(Conta conta) {

		if (conta == null) {
			return false;
		}

		if (conta.getId() <= 0) {
			return false;
		}

		if (conta.getNome() == null || conta.getNome().isEmpty()) {
			return false;
		}

		if (conta.getSaldo() < 0) {
			return false;
		}

		if (conta.getTipoConta() == null) {
			return false;
		}

		return contaDAO.atualizar(conta);
	}

	public boolean excluir(int idConta) {

		if (idConta <= 0) {
			return false;
		}

		return contaDAO.excluir(idConta);
	}

	public Conta buscarPorId(int idConta) {

		if (idConta <= 0) {
			return null;
		}

		return contaDAO.buscarPorId(idConta);
	}

	public List<Conta> listarPorUsuario(int idUsuario) {

		if (idUsuario <= 0) {
			return null;
		}

		return contaDAO.listarPorUsuario(idUsuario);
	}

	public List<Conta> listarPorNome(int idUsuario, String nome) {

		if (idUsuario <= 0) {
			return null;
		}

		if (nome == null || nome.isEmpty()) {
			return listarPorUsuario(idUsuario);
		}

		return contaDAO.listarPorNome(idUsuario, nome);
	}
}
