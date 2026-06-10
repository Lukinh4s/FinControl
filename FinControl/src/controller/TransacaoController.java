package controller;

import java.util.Date;
import java.util.List;

import dao.TransacaoDAO;
import model.Despesa;
import model.Receita;
import model.Transacao;

public class TransacaoController {

    private TransacaoDAO transacaoDAO;

    public TransacaoController() {
        this.transacaoDAO = new TransacaoDAO();
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

        return transacaoDAO.cadastrar(
                receita,
                idUsuario,
                idConta,
                idCategoria
        );
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

        return transacaoDAO.cadastrar(
                despesa,
                idUsuario,
                idConta,
                idCategoria
        );
    }

    public boolean atualizar(Transacao transacao) {

        if (transacao == null) {
            return false;
        }

        if (transacao.getId() <= 0) {
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

        if (idUsuario <= 0) {
            return null;
        }

        if (idCategoria <= 0) {
            return null;
        }

        return transacaoDAO.listarPorCategoria(
                idUsuario,
                idCategoria
        );
    }
}