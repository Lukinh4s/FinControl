package controller;

import java.sql.Date;
import java.util.List;

import dao.ContaDAO;
import dao.TransacaoDAO;
import model.Conta;
import model.Despesa;
import model.Receita;
import model.Transacao;

public class TransacaoController {

    private TransacaoDAO transacaoDAO;
    private ContaDAO contaDAO;

    public TransacaoController() {
        this.transacaoDAO = new TransacaoDAO();
        this.contaDAO = new ContaDAO();
    }

    public boolean cadastrarReceita(
            Receita receita,
            int idUsuario,
            int idConta,
            int idCategoria
    ) {

        if (receita == null) {
            return false;
        }

        if (receita.getValor() <= 0) {
            return false;
        }

        if (idUsuario <= 0 || idConta <= 0 || idCategoria <= 0) {
            return false;
        }

        Conta conta = contaDAO.buscarPorId(idConta);

        if (conta == null) {
            return false;
        }

        conta.adicionarSaldo(receita.getValor());

        boolean cadastrou = transacaoDAO.cadastrar(
                receita,
                idUsuario,
                idConta,
                idCategoria
        );

        if (cadastrou) {
            contaDAO.atualizarSaldo(conta);
        }

        return cadastrou;
    }

    public boolean cadastrarDespesa(
            Despesa despesa,
            int idUsuario,
            int idConta,
            int idCategoria
    ) {

        if (despesa == null) {
            return false;
        }

        if (despesa.getValor() <= 0) {
            return false;
        }

        if (idUsuario <= 0 || idConta <= 0 || idCategoria <= 0) {
            return false;
        }

        Conta conta = contaDAO.buscarPorId(idConta);

        if (conta == null) {
            return false;
        }

        try {
            conta.removerSaldo(despesa.getValor());
        } catch (IllegalArgumentException erro) {
            System.out.println(erro.getMessage());
            return false;
        }

        boolean cadastrou = transacaoDAO.cadastrar(
                despesa,
                idUsuario,
                idConta,
                idCategoria
        );

        if (cadastrou) {
            contaDAO.atualizarSaldo(conta);
        }

        return cadastrou;
    }

    public boolean atualizar(Transacao transacao) {

        if (transacao == null) {
            return false;
        }

        if (transacao.getId() <= 0) {
            return false;
        }

        if (transacao.getDescricao() == null || transacao.getDescricao().trim().isEmpty()) {
            return false;
        }

        if (transacao.getValor() <= 0) {
            return false;
        }

        if (transacao.getData() == null) {
            return false;
        }

        if (transacao.getUsuario() == null ||
            transacao.getConta() == null ||
            transacao.getCategoria() == null) {
            return false;
        }

        return transacaoDAO.atualizar(transacao);
    }

    public boolean excluir(int idTransacao) {

        if (idTransacao <= 0) {
            return false;
        }

        return transacaoDAO.excluir(idTransacao);
    }

    public Transacao buscarPorId(int idTransacao) {

        if (idTransacao <= 0) {
            return null;
        }

        return transacaoDAO.buscarPorId(idTransacao);
    }

    public List<Transacao> listarPorUsuario(int idUsuario) {

        if (idUsuario <= 0) {
            return null;
        }

        return transacaoDAO.listarPorUsuario(idUsuario);
    }

    public List<Transacao> listarPorPeriodo(
            int idUsuario,
            Date dataInicio,
            Date dataFim
    ) {

        if (idUsuario <= 0) {
            return null;
        }

        if (dataInicio == null || dataFim == null) {
            return null;
        }

        if (dataInicio.after(dataFim)) {
            return null;
        }

        return transacaoDAO.listarPorPeriodo(
                idUsuario,
                dataInicio,
                dataFim
        );
    }

    public List<Transacao> listarPorCategoria(
            int idUsuario,
            int idCategoria
    ) {

        if (idUsuario <= 0 || idCategoria <= 0) {
            return null;
        }

        return transacaoDAO.listarPorCategoria(
                idUsuario,
                idCategoria
        );
    }

    public double calcularSaldoPeriodo(
            int idUsuario,
            Date dataInicio,
            Date dataFim
    ) {

        List<Transacao> transacoes = listarPorPeriodo(
                idUsuario,
                dataInicio,
                dataFim
        );

        if (transacoes == null) {
            return 0;
        }

        double saldo = 0;

        for (Transacao transacao : transacoes) {
            saldo += transacao.calcularValor();
        }

        return saldo;
    }
}